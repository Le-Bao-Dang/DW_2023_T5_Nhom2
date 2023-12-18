package service;

import bean.BangxepHangAggregate;
import bean.Config;
import bean.FileData;
import bean.Log;
import db.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class LoadDataToMart {
    public static void performETL() {
        // 1. Kết nối Database Control
        List<Config> list = ConfigService.getInstance().getListConfig();
        // 2.Kết nối Database Mart và Fact
        // Lấy đối tượng Jdbi cho cơ sở dữ liệu Mart
        Jdbi jdbiMart = JDBIConnector.getMartJdbi();
        // Lấy đối tượng Jdbi cho cơ sở dữ liệu Fact
        Jdbi jdbiFact = JDBIConnector.getFactJdbi();
        // 3. Ghi log kết nối database thành công
        LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), "Kết nối database thành công", "SUCCESS"));
        try (Handle martHandle = jdbiMart.open(); Handle factHandle = jdbiFact.open()) {
            List<BangxepHangAggregate> resultIterator = null;

            FileData fileData, fileDataMart;
            //4. Duyệt qua danh sách Config để thực hiện quá trình ETL cho từng config
            for (Config config : list) {
                // 5. Cập nhật lại dữ liệu trong bảng 'aggregate' của Mart
                martHandle.createUpdate("TRUNCATE TABLE aggregate").execute();
                // 6. Ghi log bắt đầu quá trình trích xuất dữ liệu cho từng cấu hình
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + "Bắt đầu trích xuất dữ liệu từ data warehouse vào data mart", "EXTRACTING"));
                // 7. Lấy thông tin về bảng file_data cho quá trình Load data to AGG và quá trình Load Data Mart
                fileData = ConfigService.getInstance().getFileDataToDay(config.getId(), "LOAD DATA TO AGG");
                fileDataMart = ConfigService.getInstance().getFileDataToDay(config.getId(), "LOAD DATA MART");
                // 8. Kiểm tra sự tồn tại và điều kiện trạng thái của bảng file_data cho quá trình Load data to AGG
                if (fileData != null && fileData.getStatus() == 4) {
                    // 9.Trích xuất dữ liệu từ bảng 'bang_xep_hang_aggregate' của Fact
                    resultIterator = extractDataFromDW(factHandle);
                    // 10. Kiểm tra sự tồn tại và điều kiện trạng thái của bảng file_data cho quá trình Load Data Mart
                    if (fileDataMart != null && fileDataMart.getStatus() != -4) {
                        // 11. Cập nhật trạng thái của bảng file_data là đã nạp thành công
                        ConfigService.getInstance().setstatusFileData(ConfigService.LOADED, fileDataMart.getId());
                    } else if (fileDataMart == null) {
                        // 12. Tạo mới bảng file_data nếu chưa tồn tại và đặt trạng thái là đã nạp thành công

                        fileDataMart = new FileData(1, config, "LOAD DATA MART", LocalDate.now(), LocalDate.now(), LocalDateTime.now(), "Bình", "Extract từ data warehouse  đến data mart", ConfigService.LOADED);
                        ConfigService.getInstance().insertFileData(fileDataMart);
                    }
                    // 13. Ghi log thành công cho quá trình trích xuất dữ liệu
                    LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + "Trích xuất dữ liệu thành công", "SUCCESS"));
                } else {
                    // 14. Trạng thái của bảng file_data là không hợp lệ, cập nhật trạng thái là thất bại
                    ConfigService.getInstance().setstatusFileData(ConfigService.FAILED, fileDataMart.getId());
                    // 15. Ghi log thất bại cho quá trình trích xuất dữ liệu
                    LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), config.getName() + "Trích xuất dữ liệu thất bại", "FAILED"));
                }
            }



            //  Hiển thị dữ liệu đã trích xuất
            System.out.println(resultIterator);

//            // 16.Biến đổi và nạp dữ liệu vào bảng 'aggregate' của Mart
            transformAndLoadDataIntoFact(resultIterator, martHandle);

            //17. Ghi log nạp dữ liệu thành công
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), "Quá trình Load data mart thành công", "LOAD SUCCESS"));
            System.out.println("Quá trình Load data mart thành công.");
        } catch (Exception e) {
            // Ghi log và in ra màn hình nếu có bất kỳ lỗi nào xảy ra

            e.printStackTrace();
        }
    }

    //9. Trích xuất dữ liệu từ bảng 'bang_xep_hang_aggregate' của Fact
    private static List<BangxepHangAggregate> extractDataFromDW(Handle handle) {
        //  Câu truy vấn SQL để lấy toàn bộ dữ liệu từ bảng 'bang_xep_hang_aggregate'
        String extractQuery = "SELECT * FROM bang_xep_hang_aggregate";
        //  Thực hiện truy vấn và ánh xạ kết quả vào danh sách đối tượng BangxepHangAggregate
        return new ArrayList<>(handle.createQuery(extractQuery).mapToBean(BangxepHangAggregate.class).stream().toList());
    }

    //16. Biến đổi và nạp dữ liệu vào bảng 'aggregate' của Mart
    public static void transformAndLoadDataIntoFact(List<BangxepHangAggregate> resultData, Handle handle) {
        try {
            //  Câu truy vấn SQL để chèn dữ liệu vào bảng 'aggregate'
            String loadQueryAggregateMart = "INSERT INTO aggregate(hang,logo,ten_doi_bong,so_tran,tran_thang,tran_hoa,tran_thua,he_so,diem,nam_tran_gan_nhat,ten_giai_dau,ngay) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            // Duyệt dòng lặp qua dữ liệu kết quả và chèn vào database Mart
            for (BangxepHangAggregate row : resultData) {
                // Chuyển đổi chuỗi ngày thành LocalDate bằng định dạng đã xác định
                LocalDate ngay = convertToDate(row.getNgay());
                // Thực hiện câu truy vấn SQL để chèn dữ liệu
                handle.createUpdate(loadQueryAggregateMart)
                        .bind(0, row.getHang())
                        .bind(1, row.getLogo())
                        .bind(2, row.getTen_doi_bong())
                        .bind(3, row.getSo_tran())
                        .bind(4, row.getTran_thang())
                        .bind(5, row.getTran_hoa())
                        .bind(6, row.getTran_thua())
                        .bind(7, row.getHe_so())
                        .bind(8, row.getDiem())
                        .bind(9, row.getNam_tran_gan_nhat())
                        .bind(10, row.getTen_giai_dau())
                        .bind(11, ngay)
                        .execute();

            }

        } catch (Exception e) {
            //18. Ghi log nếu quá trình nạp dữ liệu thất bại
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), "Quá trình Load data thất bại", "FAILED"));
            e.printStackTrace();
        }

    }
    // Chuyển đổi một chuỗi ngày thành LocalDate sử dụng định dạng đã xác định
    private static LocalDate convertToDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateStr, formatter);
    }
    // Phương thức chính để thực hiện quá trình ETL
    public static void main(String[] args) {
        performETL();

    }
}


