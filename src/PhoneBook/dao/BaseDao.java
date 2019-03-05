package PhoneBook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {
    private String dbUser = null;
    private String dbPass = null;

    public BaseDao(String dbUser, String dbPass) {
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    protected Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 연결
            String dburl = "jdbc:oracle:thin:@localhost:1521:xe";   //db 주소
            conn = DriverManager.getConnection(dburl, dbUser, dbPass);
            //db 연결
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 찾지 못하였습니다..");
        }
        return conn; // 연결 반환
    }
}
