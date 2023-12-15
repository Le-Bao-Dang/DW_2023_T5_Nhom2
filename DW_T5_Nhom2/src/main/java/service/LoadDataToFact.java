package service;

import db.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoadDataToFact {

    private static void performETL() {
        Jdbi jdbiStaging = JDBIConnector.getStagingJdbi();
        Jdbi jdbiFact = JDBIConnector.getFactJdbi();

        try (Handle stagingHandle = jdbiStaging.open(); Handle factHandle = jdbiFact.open()) {
            // Xử lý dữ liệu cũ và Truncate các bảng trước khi nạp dữ liệu mới
            factHandle.createUpdate("SET foreign_key_checks = 0").execute();
            factHandle.createUpdate("TRUNCATE TABLE bang_xep_hang_fact").execute();
            factHandle.createUpdate("TRUNCATE TABLE doi_bong_dim").execute();
            factHandle.createUpdate("TRUNCATE TABLE giai_dau_dim").execute();
            factHandle.createUpdate("TRUNCATE TABLE thoi_gian_dim").execute();
            factHandle.createUpdate("SET foreign_key_checks = 1").execute();

            ArrayList<Map<String, Object>> result = extractDataFromStaging(stagingHandle);

            transformAndLoadDataIntoDim(result, factHandle);
            transformAndLoadDataIntoFact(result, factHandle);

            System.out.println("Quá trình ETL thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Map<String, Object>> extractDataFromStaging(Handle handle) {
        // Thực hiện truy vấn trích xuất từ bảng staging
        String extractQuery = "SELECT * FROM staging";
        return new ArrayList<>(handle.createQuery(extractQuery).mapToMap().list());
    }

    private static void transformAndLoadDataIntoFact(ArrayList<Map<String, Object>> resultData, Handle factHandle) {
        String loadQueryFact = "INSERT INTO bang_xep_hang_fact (id_doibong, id_giaidau, id_thoigian) VALUES (?, ?, ?)";

        for (Map<String, Object> row : resultData) {
            // Xác định các giá trị cần chèn vào bảng fact từ các bảng dim
            String teamName = (String) row.get("ten_doi_bong");
            String tournamentName = (String) row.get("ten_giai_dau");

            // Lấy id_doibong từ bảng doibongdim
            int teamId = factHandle.createQuery("SELECT id FROM doi_bong_dim WHERE ten_doi_bong = :teamName")
                    .bind("teamName", teamName).mapTo(Integer.class).findOne().orElse(null);


            // Lấy id_giaidau từ bảng giaidaudim
            int tournamentId = factHandle.createQuery("SELECT id FROM giai_dau_dim WHERE ten_giai_dau = :tournamentName")
                    .bind("tournamentName", tournamentName).mapTo(Integer.class).findOne().orElse(null);

            // Lấy id_thoigian
            int timeId = 1;

            // Chèn dữ liệu vào bảng fact
            factHandle.createUpdate(loadQueryFact)
                    .bind(0, teamId)
                    .bind(1, tournamentId)
                    .bind(2, timeId)
                    .execute();
        }
    }


    private static void transformAndLoadDataIntoDim(ArrayList<Map<String, Object>> resultData, Handle handle) {
        String loadQueryDoibongdim = "INSERT INTO doi_bong_dim (hang, ten_doi_bong, logo, so_tran, tran_thang, tran_hoa, tran_thua, he_so, diem, nam_tran_gan_nhat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Map để theo dõi tên đội bóng duy nhất và tránh chèn trùng lặp
        Map<String, Boolean> uniqueTeamNames = new HashMap<>();

        String loadQueryGiaidaudim = "INSERT INTO giai_dau_dim (ten_giai_dau) VALUES (?)";

        String loadQueryThoigiandim = "INSERT INTO thoi_gian_dim (day, month, year) VALUES (?, ?, ?)";

        // Map để theo dõi tên giải đấu duy nhất và tránh chèn trùng lặp
        Map<String, Boolean> uniqueTournaments = new HashMap<>();

        // Biến đếm để theo dõi số lượng giải đấu đã chèn
        int insertedTournaments = 0;

        //Format cấu trúc thời gian
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        for (Map<String, Object> row : resultData) {
            String teamName = (String) row.get("ten_doi_bong");

            // Chuyển đổi giá trị thời gian từ Map
            String thoigiancraw = (String) row.get("thoi_gian_crawl");
            LocalDateTime dateTime = LocalDateTime.parse(thoigiancraw, formatter);

            handle.createUpdate(loadQueryThoigiandim)
                    .bind(0, dateTime.getDayOfMonth())
                    .bind(1, dateTime.getMonthValue())
                    .bind(2, dateTime.getYear())
                    .execute();

            // Kiểm tra xem tên đội bóng đã tồn tại trong Map hay chưa
            if (!uniqueTeamNames.containsKey(teamName)) {
                // Chèn dữ liệu đội bóng vào bảng "doibongdim"
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
                // Thêm tên đội bóng vào Map để đảm bảo không chèn trùng lặp
                uniqueTeamNames.put(teamName, true);
            }

            // Lấy tên giải đấu từ dòng dữ liệu
            String tournamentName = (String) row.get("ten_giai_dau");

            // Kiểm tra xem tên giải đấu chưa có trong Map và số lượng giải đấu đã chèn ít hơn 4
            if (!uniqueTournaments.containsKey(tournamentName) && insertedTournaments < 4) {
                // Chèn dữ liệu giải đấu vào bảng "giaidaudim"
                handle.createUpdate(loadQueryGiaidaudim)
                        .bind(0, tournamentName)
                        .execute();

                // Thêm tên giải đấu vào Map để đảm bảo không chèn trùng lặp
                uniqueTournaments.put(tournamentName, true);

                insertedTournaments++;
            }
        }
    }
    public static void main(String[] args) {
        // Thực hiện quá trình ETL
        performETL();
    }

}