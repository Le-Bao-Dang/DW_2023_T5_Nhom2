package service;

import db.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.Map;

public class LoadDataToAggregate {
    private static void performETL() {
        // kết nối với dataFact
        Jdbi jdbiFact = JDBIConnector.getFactJdbi();

        try (Handle factHandle = jdbiFact.open()) {
            // Xử lý dữ liệu cũ và Truncate các bảng trước khi nạp dữ liệu mới
            factHandle.createUpdate("TRUNCATE TABLE bang_xep_hang_aggregate").execute();

            ArrayList<Map<String, Object>> result = extractDataFromFact(factHandle);
            
            transformAndLoadDataIntoAggregate(result, factHandle);

            System.out.println("Quá trình ETL thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void transformAndLoadDataIntoAggregate(ArrayList<Map<String, Object>> result, Handle factHandle) {
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
