package service;

import bean.Config;
import bean.FileData;
import bean.Log;
import db.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LoadDataToFact {

    public static void performETL() {
        //1. Kết nối với các cơ sở dữ liệu Control, Staging, Fact
        Jdbi jdbiStaging = JDBIConnector.getStagingJdbi();
        Jdbi jdbiFact = JDBIConnector.getFactJdbi();
        List<Config> list = ConfigService.getInstance().getListConfig();
        //1.1 Ghi Log kết nối database thành công
        LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), "kết nối database thành công",
                "SUCCESS"));
        FileData fileData, fileDataFact;
        try (Handle stagingHandle = jdbiStaging.open(); Handle factHandle = jdbiFact.open()) {
            ArrayList<Map<String, Object>> result = null;
            for (Config config : list) {
                //2. Xử lý dữ liệu cũ và Truncate các bảng trước khi nạp dữ liệu mới
                factHandle.createUpdate("SET foreign_key_checks = 0").execute();
                factHandle.createUpdate("TRUNCATE TABLE bang_xep_hang_fact").execute();
                factHandle.createUpdate("TRUNCATE TABLE doi_bong_dim").execute();
                factHandle.createUpdate("TRUNCATE TABLE giai_dau_dim").execute();
                factHandle.createUpdate("TRUNCATE TABLE thoi_gian_dim").execute();
                factHandle.createUpdate("SET foreign_key_checks = 1").execute();
                //3. Ghi Log table "Bắt đầu trích xuất dữ liệu"
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + " Bắt đầu trích xuất dữ liệu từ Staging vào Fact",
                        "EXTRACTING"));
                fileData = ConfigService.getInstance().getFileDataToDay(config.getId(), "CRAWL DATA");
                fileDataFact = ConfigService.getInstance().getFileDataToDay(config.getId(), "ETL DATA");
                //4. Kiểm tra sự tồn tại và trạng thái  của quá trình "CRAWL DATA" trong file_data table
                if (fileData != null && fileData.getStatus() == 3) {
                    //5. Thực thi trích xuất dữ liệu
                    result = extractDataFromStaging(stagingHandle);
                    //6. Kiểm tra sự tồn tại và trạng thái  của quá trình "ETL DATA" trong file_data table
                    if (fileDataFact != null && fileDataFact.getStatus() != 4) {
                        //7. Cập nhật trạng thái LOADED của "ETL DATA" trong file_data table
                        ConfigService.getInstance().setstatusFileData(ConfigService.LOADED, fileDataFact.getId());
                    } else if (fileDataFact == null) {
                        //8. Tạo dòng mới và set trạng thái LOADED cho nó trong file_data table
                        fileDataFact = new FileData(1, config, "ETL DATA", LocalDate.now(), LocalDate.now(),
                                LocalDateTime.now(), "Dũng", "Load từ Staging  đến DataWareHouseCore", ConfigService.LOADED);
                        ConfigService.getInstance().insertFileData(fileDataFact);
                    }
                    //9. Ghi vào Log table "Quá trình ETL dữ liệu thành công."
                    LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + " Quá trình ETL dữ liệu thành công.",
                            "SUCCESS"));
                } else {
                    //10. Ghi vào log table "Quá trình ETL dữ liệu thất bại."
                    LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + " Quá trình ETL dữ liệu thất bại.",
                            "FAILED"));
                }
                if (result != null) {
                    //11. Gọi hàm transform và load từ staging vào Dim
                    transformAndLoadDataIntoDim(result, factHandle);
                    //12. Gọi hàm transform và load từ Dim vào Fact
                    transformAndLoadDataIntoFact(result, factHandle);
                    LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), " Quá trình ETL dữ liệu vào Fact thành công.",
                            "SUCCESS"));
                }
            }
        } catch (Exception e) {
            //13. Ghi Log  "Quá trình ETL vào Fact dữ liệu thất bại."
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now()," Quá trình ETL vào Fact dữ liệu thất bại.",
                    "FAILED"));
            e.printStackTrace();
        }
    }

    private static ArrayList<Map<String, Object>> extractDataFromStaging(Handle handle) {
        // Thực hiện truy vấn trích xuất từ bảng staging
        String extractQuery = "SELECT * FROM staging";
        return new ArrayList<>(handle.createQuery(extractQuery).mapToMap().list());
    }
    private static void transformAndLoadDataIntoDim(ArrayList<Map<String, Object>> resultData, Handle handle) {
        //11.1 Query chèn dữ liệu vào các bảng dim
        String loadQueryDoibongdim = "INSERT INTO doi_bong_dim (hang, ten_doi_bong, logo, so_tran, tran_thang, tran_hoa, tran_thua, he_so, diem, nam_tran_gan_nhat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String loadQueryGiaidaudim = "INSERT INTO giai_dau_dim (ten_giai_dau) VALUES (?)";
        String loadQueryThoigiandim = "INSERT INTO thoi_gian_dim (day, month, year) VALUES (?, ?, ?)";
        //11.2 Map để theo dõi tên đội bóng và tên giải đấu là duy nhất
        Map<String, Boolean> uniqueTeamNames = new HashMap<>();
        Map<String, Boolean> uniqueTournaments = new HashMap<>();
        //11.3 Biến đếm để theo dõi số lượng giải đấu đã chèn
        int insertedTournaments = 0;

        //11.4 Format cấu trúc thời gian
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        for (Map<String, Object> row : resultData) {
            String teamName = (String) row.get("ten_doi_bong");

            //11.5 Chuyển đổi giá trị thời gian từ Map
            String thoigiancraw = (String) row.get("thoi_gian_crawl");
            LocalDateTime dateTime = LocalDateTime.parse(thoigiancraw, formatter);

            handle.createUpdate(loadQueryThoigiandim)
                    .bind(0, dateTime.getDayOfMonth())
                    .bind(1, dateTime.getMonthValue())
                    .bind(2, dateTime.getYear())
                    .execute();

            //11.6 Kiểm tra xem tên đội bóng đã tồn tại trong Map hay chưa
            if (!uniqueTeamNames.containsKey(teamName)) {
                // 11.6.1 Chèn dữ liệu đội bóng vào bảng "doibongdim"
                handle.createUpdate(loadQueryDoibongdim)
                        .bind(0, row.get("hang"))
                        .bind(1, teamName)
                        .bind(2, row.get("logo"))
                        .bind(3, row.get("so_tran"))
                        .bind(4, row.get("tran_thang"))
                        .bind(5, row.get("tran_hoa"))
                        .bind(6, row.get("tran_thua"))
                        .bind(7, row.get("he_so"))
                        .bind(8, row.get("diem"))
                        .bind(9, row.get("nam_tran_gan_nhat"))
                        .execute();
                uniqueTeamNames.put(teamName, true);
            }else {
                //11.7 Ghi log table nếu không thỏa mãn điều kiện
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now()," Quá trình ETL vào Fact dữ liệu thất bại.",
                        "FAILED"));
            }

            // Lấy tên giải đấu từ dòng dữ liệu
            String tournamentName = (String) row.get("ten_giai_dau");

            //11.8 Kiểm tra xem tên giải đấu đã tồn tại trong Map hay chưa  và số lượng giải đấu đã chèn ít hơn 4
            if (!uniqueTournaments.containsKey(tournamentName) && insertedTournaments < 4) {
                //11.8.1 Chèn dữ liệu giải đấu vào bảng "giaidaudim"
                handle.createUpdate(loadQueryGiaidaudim)
                        .bind(0, tournamentName)
                        .execute();
                uniqueTournaments.put(tournamentName, true);
                insertedTournaments++;
            }
            else {
                //11.7 Ghi log table nếu không thỏa mãn điều kiện
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now()," Quá trình ETL vào Fact dữ liệu thất bại.",
                        "FAILED"));
            }
        }
    }
    private static void transformAndLoadDataIntoFact(ArrayList<Map<String, Object>> resultData, Handle factHandle) {

        try{
            //12.1 Query để chèn dữ liệu vào bảng Fact
            String loadQueryFact = "INSERT INTO bang_xep_hang_fact (id_doibong, id_giaidau, id_thoigian) VALUES (?, ?, ?)";

            for (Map<String, Object> row : resultData) {
                //12.2 Xác định các giá trị cần chèn vào bảng fact từ các bảng dim
                String teamName = (String) row.get("ten_doi_bong");
                String tournamentName = (String) row.get("ten_giai_dau");

                //12.3 Lấy id_doibong từ bảng doibongdim
                int teamId = factHandle.createQuery("SELECT id FROM doi_bong_dim WHERE ten_doi_bong = :teamName")
                        .bind("teamName", teamName).mapTo(Integer.class).findOne().orElse(null);


                //12.4 Lấy id_giaidau từ bảng giaidaudim
                int tournamentId = factHandle.createQuery("SELECT id FROM giai_dau_dim WHERE ten_giai_dau = :tournamentName")
                        .bind("tournamentName", tournamentName).mapTo(Integer.class).findOne().orElse(null);

                //12.5 Lấy id_thoigian
                int timeId = 1;

                //12.6 Chèn dữ liệu vào bảng fact
                factHandle.createUpdate(loadQueryFact)
                        .bind(0, teamId)
                        .bind(1, tournamentId)
                        .bind(2, timeId)
                        .execute();
            }
        }catch (Exception e){
            //12.7 Ghi log table nếu chèn dữ liệu thất bại
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), " Quá trình ETL dữ liệu thất bại.",
                    "FAILED"));
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // Thực hiện quá trình ETL
        performETL();
    }

}