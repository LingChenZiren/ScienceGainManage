package com.example.demo.JDBCController;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 测试连接与关闭数据库
 */
public class ConnectionDemo {
    //    public static void main(String[] args) {
//        // String driverClassName=com.mysql.jdbc.Driver//MySql5驱动程序引用
//        String driverClassName = "com.mysql.cj.jdbc.Driver";//MySql8驱动程序引用
//        try {
//            Class.forName(driverClassName);//加载驱动程序
//            System.out.println("MySql8数据库驱动加载成功！");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
/*
        public static void main(String[] args) throws Exception {
            String driverClassName = "com.mysql.cj.jdbc.Driver";//驱动程序引用
            // 定义MySQL数据库的连接地址
            String dburl = "jdbc:mysql://localhost:3306/science_inquiry";
            // MySQL数据库的连接用户名
            String dbuser = "root";
            // MySQL数据库的连接密码
            String dbpassword = "123456";
            Class.forName(driverClassName);//加载驱动程序
            System.out.println("MySql8数据库驱动加载成功！");
            //创建数据库连接
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
            System.out.println(conn);
            conn.close();//关闭数据库连接
        }*/
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();//使用工具类建立数据库连接
        System.out.println("准备执行数据库操作");
        conn.close();//关闭连接
        System.out.println(conn.isClosed());//数据库连接是否关闭
    }

}