package service;

import bean.Config;
import bean.FileData;
import bean.Log;
import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScriptCrawData {
    public static void crawlerDataformConfig() {
        List<Config> list = ConfigService.getInstance().getListConfig();
        FileData fileData;
        // 4. Lấy từng dòng file trong bảng config
        for (Config c : list) {
            System.out.println(c.getId());
            fileData = ConfigService.getInstance().getFileDataToDay(c.getId());
            //5. kiểm tra đã có dữ liệu trong bảng file_data
            if (fileData == null) { // Nếu chưa có file_data thì thêm file_data vào
                // Thêm vào log bắt đầu nạp dữ liệu
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), "bắt đầu crawl dữ liệu", "CRAWLING"));
                //7. Thêm dữ liệu vào vào bảng file_data với status= "PREPARE"
                fileData = new FileData(1, c, "CRAWL DATA", LocalDate.now(), LocalDate.now(), LocalDateTime.now(), "Đặng", "crawl dữ liệu từ website", ConfigService.PREPARE);
                ConfigService.getInstance().insertFileData(fileData);
            }
            // 6-8. Kiểm tra status trong bảng file_data có đang CRAWL dữ liệu hay không
            if (fileData.getStatus() != 1) { // kiểm tra status xem dòng dữ liệu hiện có đang chạy hay không
                //9. Cập nhật status trong log thành CRAWLLING
                ConfigService.getInstance().setstatusFileData(ConfigService.CRAWLING, fileData.getId());
                // Tiến hành crawl dữ liệu
                crawlerData(c.getSource_url(), c.getSource_path(), c.getName(), c.getFile_format(), LocalDate.now(), fileData.getId());
            }

        }
    }

    public static void crawlerData(String url, String sourceFile, String namefile, String fileFormat, LocalDate create_date, int idFileData) {
        try {
            // 10. Lấy đường dẫn URL của website
            Document document = Jsoup.connect(url).get();
            // 11. Kiểm tra đường dẫn có lỗi hay không
            if (document == null) {
                // 13. Nếu đường dẫn có lỗi xãy ra cập nhật status vào bảng file_data
                ConfigService.getInstance().setstatusFileData(ConfigService.FAILED, idFileData);
                // 14. Ghi lỗi vào bảng log
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), namefile +"Lỗi đường dẫn website khi crawl data", "FAILED"));
                return;
            }

            //12. Tiến hành đọc dữ liệu các thẻ html của website
            String title = document.select("h2.title-giaidau").text();
            System.out.println("Title: " + title);

            // Đặt tên cho cột chứa đường dẫn hình ảnh (ví dụ: cột thứ 2)
            int imageColumnIndex = 1;

            Element tableBXH = document.select("table.table-bxh").first();
            Elements rows = tableBXH.select("tr");

            List<String[]> listDataRow = new ArrayList<>();
            //15. Duyệt lần lượt các dòng dữ liệu
            for (Element row : rows) {
                // 16. Lấy một dòng dữ liệu
                Elements cells = row.select("td");
                if (cells.text().isEmpty()) continue;

                //17. Lưu 1 dòng dữ liệu vào dạng mãng String
                String[] rowData = new String[cells.size() + 4];
                // 18. Duyệt đến khi hết dòng dữ liệu
                for (int i = 0; i < cells.size(); i++) {
                    if (i < imageColumnIndex) {
                        rowData[i] = cells.get(i).text();
                    } else {
                        rowData[i + 1] = cells.get(i).text();
                    }
                }

                // lưu Url hình ảnh vào mãng
                String imageUrl = getImageUrlFromCell(cells.get(imageColumnIndex));
                rowData[imageColumnIndex] = imageUrl;

                // Lưu thời gian vào mãng
                LocalDateTime dt = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
                rowData[rowData.length - 2] = dt.format(formatter);

                // thông tin của các đội
                rowData[rowData.length - 1] = getLast5Matches(cells);
                // tên giải đấu
                rowData[rowData.length - 3] = title;

                // năm trận gần nhất
//                rowData[rowData.length - 1] = getLast5Matches(cells);

                // 19.lưu tất cả dòng dữ liệu vào list
//                csvWriter.writeNext(rowData);
                listDataRow.add(rowData);
            }
            //20. Đọc đường dẫn file csv từ bảng config
            File file = new File(sourceFile + create_date + "_" + namefile + fileFormat);
            //21. Kiểm tra lỗi đường dẫn hay không
            if (file.getPath() == null) {
                // 13 cập nhật status trong bảng file_data
                ConfigService.getInstance().setstatusFileData(ConfigService.FAILED, idFileData);
                // 14 Ghi lỗi vào bảng log
                LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), namefile +"Lỗi file không tồn tại", "FAILED"));
                return;
            }
            System.out.println(file.getPath());
            FileWriter fileWriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            //22. Lưu tất cả vào dữ liệu vào file.csv
            for (String[] s : listDataRow) {
                csvWriter.writeNext(s);
            }
            //23. Đóng file
            csvWriter.close();
            fileWriter.close();
            //24. Cập nhật dữ liệu trong bảng file_data status= EXTRACTED
            ConfigService.getInstance().setstatusFileData(ConfigService.SUCCESS, idFileData);
            //25. Ghi nhận vào bảng log crawl dữ liệu thành công
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), namefile + "CRAWL data thành công", "SUCCESS"));
            System.out.println("Data has been written to output.csv");
        } catch (IOException e) {
            LogService.getInstance().addLog(new Log(1, LocalDateTime.now(), namefile +"nạp dữ liệu thất bại", "FAILED"));
            System.out.println("CRAWL DATA FAILED");
        }
    }

    public static void main(String[] args) {
        crawlerDataformConfig();
    }

    // Thêm phương thức để lấy đường dẫn hình ảnh từ cell
    private static String getImageUrlFromCell(Element cell) {
        // Điều chỉnh selector tùy thuộc vào cách dữ liệu hình ảnh được đặt trong HTML
        Element imageElement = cell.select("img").first();
        if (imageElement != null) {
            // Lấy đường dẫn từ thuộc tính src hoặc data-src
            return imageElement.attr("src");
        }
        return "";
    }
    private static String getLast5Matches(Elements cells) {
        return "";

    }
}
