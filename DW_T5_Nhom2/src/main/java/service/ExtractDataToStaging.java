package service;

import bean.Config;
import bean.FileData;
import bean.Log;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import db.JDBIConnector;
import org.jdbi.v3.core.Handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ExtractDataToStaging {
    public static void loadDataToStaging() {
        FileData fileData;
        FileData fileDataSta;
        //        1. kết nối control database
        List<Config> list = ConfigService.getInstance().getListConfig();
        try (Handle handle = JDBIConnector.getStagingJdbi().open()) {
            handle.createUpdate("TRUNCATE TABLE staging")
                    .execute();
        }
//        2. duyệt qua từng dòng dữ liệu trong bản config
        for (Config config : list) {
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + " Bắt đầu trích xuất dữ liệu từ file vào staging",
                    "EXTRACTING"));
            fileData = ConfigService.getInstance().getFileDataToDay(config.getId(), "CRAWL DATA");
            fileDataSta = ConfigService.getInstance().getFileDataToDay(config.getId(), "EXTRACT DATA");
            if (fileData != null && fileData.getStatus() == 2) {
                if (fileDataSta == null) {
                    fileDataSta = new FileData(1, config, "EXTRACT DATA", LocalDate.now(), LocalDate.now(), LocalDateTime.now(), "Đặng", "trích xuất dữ liệu từ file", ConfigService.PREPARE);
                    ConfigService.getInstance().insertFileData(fileDataSta);
                    continue;
                }
                if (fileDataSta != null || fileDataSta.getStatus() != -3) {
                    ConfigService.getInstance().setstatusFileData(ConfigService.EXTRACTING, fileDataSta.getId());
                    loadDataformFileToStaging(config.getName(), config.getSource_path(), config.getFile_format(), LocalDate.now(), fileDataSta.getId());
                    ConfigService.getInstance().setstatusFileData(ConfigService.EXTRACTED, fileDataSta.getId());
                    LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + "Trích xuất dữ liệu thành công", "EXTRACTED"));
                }
            } else {
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + "Dữ liệu không sẳn sàn để trích xuất", "FAILED"));
            }
        }
    }

    public static void loadDataformFileToStaging(String fileName, String path, String formatFile, LocalDate date, int idFileData) {
//        3. Lấy đường dẫn file csv
        String csvFilePath = path + date + "_" + fileName + formatFile;
        File files = new File(csvFilePath);
        // kiểm tra đường dẫn nếu lỗi ghi lỗi vào log
        if (!files.exists()) {
            ConfigService.getInstance().setstatusFileData(ConfigService.FAILED, idFileData);
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), fileName + "trích xuất dữ liệu thất bại vì file không tồn tại", "FAILED"));
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(files));
             CSVReader csvReader = new CSVReader(reader)) {

            // Đọc file và lưu tất cả dòng dữ liệu thành một list
            List<String[]> allLines = csvReader.readAll();

            // Duyệt qua từng dòng dữ liệu
            for (String[] line : allLines) {
                StringBuilder stringBuilderSQL = new StringBuilder("Insert into staging(hang,logo,ten_doi_bong,so_tran,tran_thang,tran_hoa,tran_thua,he_so,diem,nam_tran_gan_nhat,ten_giai_dau,thoi_gian_crawl) values (");
                // Duyệt các dữ liệu trên 1 dòng
                for (int i = 0; i < line.length - 2; i++) {
                    stringBuilderSQL.append("'" + line[i] + "',");
                }
                stringBuilderSQL.append("'" + line[line.length - 2] + "');");
                // Lưu dữ liệu vào bảng staging
                try (Handle handle = JDBIConnector.getStagingJdbi().open()) {
                    handle.createUpdate(stringBuilderSQL.toString())
                            .execute();
                }
            }
            ConfigService.getInstance().setstatusFileData(ConfigService.EXTRACTED, idFileData);
        } catch (IOException | CsvException e) {
            ConfigService.getInstance().setstatusFileData(ConfigService.FAILED, idFileData);
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), fileName + "trích xuất dữ liệu thất bại", "FAILED"));

        }
    }

    public static void main(String[] args) {
        loadDataToStaging();
    }
}
