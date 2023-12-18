package service;

import bean.Config;
import bean.FileData;
import bean.Log;
import db.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadDataToAggregate {
    public static void performETL() {
        //14. Kết nối với các cơ sở dữ liệu Control,  Fact
        Jdbi jdbiFact = JDBIConnector.getFactJdbi();
        List<Config> list = ConfigService.getInstance().getListConfig();
        FileData fileDataFact, fileDataAgg;
        ArrayList<Map<String, Object>> result;
        try (Handle factHandle = jdbiFact.open()) {
            for(Config config : list) {
                //15. Xử lý dữ liệu cũ và Truncate các bảng trước khi nạp dữ liệu mới
                factHandle.createUpdate("TRUNCATE TABLE bang_xep_hang_aggregate").execute();
                //16. Ghi log table " Bắt đầu trích xuất dữ liệu từ Fact vào Agggregate"
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + " Bắt đầu trích xuất dữ liệu từ Fact vào Agggregate",
                        "EXTRACTING"));
                fileDataFact = ConfigService.getInstance().getFileDataToDay(config.getId(), "ETL DATA");
                fileDataAgg = ConfigService.getInstance().getFileDataToDay(config.getId(), "LOAD DATA TO AGG");
                //17. Kiểm tra sự tồn tại và trạng thái của quá trình "ETL DATA" trong file_data table
                if(fileDataFact!= null && fileDataFact.getStatus()==4) {
                    //18. Thực thi trích xuất dữ liệu
                    result = extractDataFromFact(factHandle);
                    //19. Kiểm tra sự tồn tại và trạng thái  của quá trình "ETL DATA" trong file_data table
                    if(fileDataAgg != null && fileDataAgg.getStatus() != 4){
                        //20. Cập nhật trạng thái LOADED của "LOAD DATA TO AGG" trong file_data table
                    ConfigService.getInstance().setstatusFileData(ConfigService.LOADED, fileDataAgg.getId());
                    }else if(fileDataAgg == null){
                        //21. Tạo dòng mới và set trạng thái LOADED cho nó trong file_data table
                        fileDataAgg = new FileData(1, config, "LOAD DATA TO AGG", LocalDate.now(), LocalDate.now(),
                                LocalDateTime.now(), "Dũng", "Load dữ liệu từ Fact vào Agggregate", ConfigService.LOADED);
                        ConfigService.getInstance().insertFileData(fileDataAgg);
                    }
                    //22. Ghi log table "Load dữ liệu vào Agggregate thành công"
                    LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + " Load dữ liệu vào Agggregate thành công",
                            "LOADED"));

                    if(result != null){
                        //23. Gọi hàm transform và load từ Fact vào Aggregate
                    transformAndLoadDataIntoAggregate(result, factHandle);}
                }else {
                    //24. Ghi log table "Load dữ liệu vào Agggregate thất bại"
                    LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + " Load dữ liệu vào Agggregate thất bại",
                            "FAILED"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void transformAndLoadDataIntoAggregate(ArrayList<Map<String, Object>> result, Handle factHandle) {
        //23.1 Thực thi chèn dữ liệu vào Aggregate
        for (Map<String, Object> row : result) {
            String fullTime = (String) row.get("full_date");
            factHandle.createUpdate("INSERT INTO bang_xep_hang_aggregate (hang, ten_doi_bong, logo, " +
                            "so_tran, tran_thang, tran_hoa, tran_thua, he_so, diem, nam_tran_gan_nhat, ten_giai_dau, ngay)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                    .bind(0, row.get("hang"))
                    .bind(1, row.get("ten_doi_bong"))
                    .bind(2, row.get("logo"))
                    .bind(3, row.get("so_tran"))
                    .bind(4, row.get("tran_thang"))
                    .bind(5, row.get("tran_hoa"))
                    .bind(6, row.get("tran_thua"))
                    .bind(7, row.get("he_so"))
                    .bind(8, row.get("diem"))
                    .bind(9, row.get("nam_tran_gan_nhat"))
                    .bind(10, row.get("ten_giai_dau"))
                    .bind(11, fullTime)
                    .execute();
        }
    }

    private static ArrayList<Map<String, Object>> extractDataFromFact(Handle factHandle) {
        // Thực hiện truy vấn trích xuất từ bảng bang_xep_hang_fact
        String extractQuery = "SELECT bang_xep_hang_fact.*, doi_bong_dim.hang, doi_bong_dim.ten_doi_bong, doi_bong_dim.logo, doi_bong_dim.so_tran, " +
                "doi_bong_dim.tran_thang, doi_bong_dim.tran_hoa, doi_bong_dim.tran_thua, doi_bong_dim.he_so, doi_bong_dim.diem," +
                "doi_bong_dim.nam_tran_gan_nhat,giai_dau_dim.ten_giai_dau, thoi_gian_dim.day, thoi_gian_dim.month, thoi_gian_dim.year, " +
                "CONCAT(LPAD(thoi_gian_dim.day, 2, '0'), '-', LPAD(thoi_gian_dim.month, 2, '0'), '-', thoi_gian_dim.year) AS full_date " +
                "FROM bang_xep_hang_fact " +
                "INNER JOIN doi_bong_dim ON bang_xep_hang_fact.id_doibong = doi_bong_dim.id " +
                "INNER JOIN giai_dau_dim ON bang_xep_hang_fact.id_giaidau = giai_dau_dim.id " +
                "INNER JOIN thoi_gian_dim ON bang_xep_hang_fact.id_thoigian = thoi_gian_dim.id;";
        return new ArrayList<>(factHandle.createQuery(extractQuery).mapToMap().list());
    }

    public static void main(String[] args) {
        // Thực hiện quá trình ETL
        performETL();
    }
}
