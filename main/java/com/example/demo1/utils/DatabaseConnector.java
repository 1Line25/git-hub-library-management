package com.example.demo1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB";
    private static final String USER = "sa"; // Tên người dùng
    private static final String PASSWORD = "yourpassword"; // Mật khẩu của người dùng

    private static Connection connection;

    static {
        try {
            // Tải driver JDBC cho SQL Server nếu cần thiết (cho các phiên bản cũ của Java)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Phương thức này trả về kết nối đến cơ sở dữ liệu
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Kết nối với cơ sở dữ liệu bằng JDBC URL, USER và PASSWORD
                Properties properties = new Properties();
                properties.setProperty("user", USER);
                properties.setProperty("password", PASSWORD);

                connection = DriverManager.getConnection(URL, properties);
                System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Không thể kết nối đến cơ sở dữ liệu. Lý do: " + e.getMessage());
            }
        }
        return connection;
    }

    // Phương thức này đóng kết nối khi không còn cần thiết
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Đã đóng kết nối.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Không thể đóng kết nối. Lý do: " + e.getMessage());
            }
        }
    }

    // Kiểm tra kết nối và khởi tạo nếu chưa có
    public static boolean isConnectionValid() {
        try {
            if (connection != null && !connection.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
