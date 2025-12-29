package vn.edu.hcmuaf.fit.Web_Shop.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    private static final String URL =
            "jdbc:mysql://localhost:3306/petshop?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // đổi theo máy bạn

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}