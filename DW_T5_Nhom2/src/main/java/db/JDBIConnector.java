package db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;

public class JDBIConnector {
    private static Jdbi jdbiStaging;
    private static Jdbi jdbiFact;
    private static Jdbi jdbiControl;
    private static Jdbi jdbiMart;
    private static void connectMart() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DBProperties.hostMart + ":" + DBProperties.portMart + "/" + DBProperties.dbnameMart);
        dataSource.setUser(DBProperties.usernameMart);
        dataSource.setPassword(DBProperties.passMart);
        try {
            dataSource.setAutoReconnect(true);
            dataSource.setUseCompression(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        jdbiMart = Jdbi.create(dataSource);
    }

    private static void connectStaging() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DBProperties.hostStaging + ":" + DBProperties.portStaging + "/" + DBProperties.dbnameStaging);
        dataSource.setUser(DBProperties.usernameStaging);
        dataSource.setPassword(DBProperties.passStaging);
        try {
            dataSource.setAutoReconnect(true);
            dataSource.setUseCompression(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        jdbiStaging = Jdbi.create(dataSource);
    }

    private static void connectFact() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DBProperties.hostFact + ":" + DBProperties.portFact + "/" + DBProperties.dbnameFact);
        dataSource.setUser(DBProperties.usernameFact);
        dataSource.setPassword(DBProperties.passFact);
        try {
            dataSource.setAutoReconnect(true);
            dataSource.setUseCompression(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        jdbiFact = Jdbi.create(dataSource);
    }

    private static void connectControl() {
        //1. Đọc thông tin kết nối database control
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DBProperties.hostControl + ":" + DBProperties.portControl + "/" + DBProperties.dbnameControl);
        dataSource.setUser(DBProperties.usernameControl);
        dataSource.setPassword(DBProperties.passControl);
        int i = 0;
        // Lặp lại tối đa 5 lần
        while (i < 5) {

            try {
                dataSource.setAutoReconnect(true);
                dataSource.setUseCompression(true);
                System.out.println("reconnect " + i);
                break;
            } catch (SQLException e) {    //3. Kiểm tra lỗi kết nối
                i++;
                System.out.println("reconnect " + i);
                // Kiểm tra lần kết nối nếu là lần thứ 5 thì dừng
                if(i==5){
                    System.out.println("connect failed after ten reconnect");
                    System.out.println("stop script");
                }
//                throw new RuntimeException(e);
            }
        }
        jdbiControl = Jdbi.create(dataSource);

    }

    //2. Kết nối database control
    public static Jdbi getControlJdbi() {
        if (jdbiControl == null) {
            connectControl();
        }
        return jdbiControl;
    }

    public static Jdbi getStagingJdbi() {
        if (jdbiStaging == null) {
            connectStaging();
        }
        return jdbiStaging;
    }

    public static Jdbi getFactJdbi() {
        if (jdbiFact == null) {
            connectFact();
        }
        return jdbiFact;
    }
    public static Jdbi getMartJdbi() {
        if (jdbiMart == null) {
            connectMart();
        }
        return jdbiMart;
    }


    public static void main(String[] args) {
//        connectStaging();
//        connectFact();
        connectControl();
    }
}
