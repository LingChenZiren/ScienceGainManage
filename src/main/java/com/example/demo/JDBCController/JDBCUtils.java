package com.example.demo.JDBCController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 定义工具类，实现连接数据库方法，如static Connection getConnection();
 */
public class JDBCUtils {
    static String url = "jdbc:mysql://localhost:3306/science_inquiry";//定义数据连接字符串
    static String username = "root";//定义数据库连接用户名
    static String password = "123456";//定义数据库连接用密码

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载MySql8数据库驱动程序
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
