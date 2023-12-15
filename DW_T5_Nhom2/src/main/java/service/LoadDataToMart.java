package service;

import bean.BangxepHangAggregate;
import bean.Log;
import db.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoadDataToMart {
    // Thực hiện quá trình ETL (Extract, Transform, Load)
    public static void performETL() {
//     2.Kết nối Database Mart và Fact
        // 3. Lấy đối tượng Jdbi cho cơ sở dữ liệu Mart
        Jdbi jdbiMart = JDBIConnector.getMartJdbi();
        // 4. Lấy đối tượng Jdbi cho cơ sở dữ liệu Fact
        Jdbi jdbiFact = JDBIConnector.getFactJdbi();

        try (Handle martHandle = jdbiMart.open(); Handle factHandle = jdbiFact.open()) {
            // 5. Cập nhật lại dữ liệu trong bảng 'aggregate' của Mart
            martHandle.createUpdate("TRUNCATE TABLE aggregate").execute();
            // 6.Trích xuất dữ liệu từ bảng 'bang_xep_hang_aggregate' của Fact
            List<BangxepHangAggregate> resultIterator = extractDataFromDW(factHandle);
            // 7. Ghi log kết nối database thành công
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(),"Kết nối database thành công","SUCCESS"));
            // 8. Hiển thị dữ liệu đã trích xuất
            System.out.println(resultIterator);

            // 9.Biến đổi và nạp dữ liệu vào bảng 'aggregate' của Mart
          if(  transformAndLoadDataIntoFact(resultIterator, martHandle) == -1) {
              //10. Ghi log nếu quá trình nạp dữ liệu thất bại
              LogService.getInstance().addLog(new Log(1, LocalDateTime.now(),"Quá trình Load data thất bại","FAILED"));

          }
            //11. Ghi log nạp dữ liệu thành công
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(),"Quá trình Load data thành công","LOAD SUCCESS"));
            System.out.println("Quá trình Load data thành công.");
        } catch (Exception e) {
            // Ghi log và in ra màn hình nếu có bất kỳ lỗi nào xảy ra
            e.printStackTrace();
        }
    }

    // Trích xuất dữ liệu từ bảng 'bang_xep_hang_aggregate' của Fact
    private static List<BangxepHangAggregate> extractDataFromDW(Handle handle) {
        String extractQuery = "SELECT * FROM bang_xep_hang_aggregate";
        return new ArrayList<>(handle.createQuery(extractQuery).mapToBean(BangxepHangAggregate.class).stream().collect(Collectors.toList()));
    }
    // Biến đổi và nạp dữ liệu vào bảng 'aggregate' của Mart
    public static int  transformAndLoadDataIntoFact(List<BangxepHangAggregate> resultData, Handle handle) {
        // Câu truy vấn SQL để chèn dữ liệu vào bảng 'aggregate'
        String loadQueryAggregateMart = "INSERT INTO aggregate(hang,logo,ten_doi_bong,so_tran,tran_thang,tran_hoa,tran_thua,he_so,diem,nam_tran_gan_nhat,ten_giai_dau,ngay) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        int i=0;
        // Lặp qua dữ liệu kết quả và chèn vào database Mart
        for (BangxepHangAggregate row : resultData) {
            // Chuyển đổi chuỗi ngày thành LocalDate bằng định dạng đã xác định
            LocalDate ngay = convertToDate(row.getNgay());
            // Thực hiện câu truy vấn SQL để chèn dữ liệu
         i=   handle.createUpdate(loadQueryAggregateMart)
                    .bind(0, row.getHang())
                    .bind(1, row.getLogo())
                    .bind(2,row.getTen_doi_bong())
                    .bind(3,row.getSo_tran())
                    .bind(4,row.getTran_thang())
                    .bind(5,row.getTran_hoa())
                    .bind(6,row.getTran_thua())
                    .bind(7,row.getHe_so())
                    .bind(8,row.getDiem())
                    .bind(9,row.getNam_tran_gan_nhat())
                    .bind(10,row.getTen_giai_dau())
                    .bind(11,ngay)
                    .execute();

        }
        return i;


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
