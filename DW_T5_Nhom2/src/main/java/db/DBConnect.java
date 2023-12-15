package db;

import java.sql.*;

public class DBConnect {
    private static DBConnect instance;
    private static String DB_URL = "jdbc:mysql://localhost/control";

    private static final String USER = "root";
    private static final String PASS = "1234";

    private Connection connection;

    private DBConnect(){}
    public static DBConnect getInstance(){
        if(instance==null)
            instance = new DBConnect();
        return instance;
    }

    private void connect() throws SQLException, ClassNotFoundException {
        if(connection==null||connection.isClosed()){
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(DB_URL,USER,PASS);
        }
    }

    public Statement get() {
        try {
            connect();
            return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws SQLException {
        DBConnect dbConnect = DBConnect.getInstance();
        Statement statement=dbConnect.get();
        ResultSet re= statement.executeQuery("select * from bangxephangstaging" );
        re.last();
        System.out.println(re.getRow());
    }

}
