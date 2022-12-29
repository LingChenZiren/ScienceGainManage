package com.example.demo.JDBCController;

import com.example.demo.pojo.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC增删查改
 */
public class PreparedStatementDemo {
    /*public static void main(String[] args) {
        Person xiaoming = new Person("89", "jkhdsjkhgf", "小红", "武汉大学", "讲师", "210", "男");
        Person Jack = new Person("356", "jusdyhfujf", "杰克", "北京大学", "教授", "220", "男");
        update(xiaoming);
        add(Jack);
        del("5");
    }*/

    /**
     * 添加相关信息
     *
     * @param per 传入的实体类
     */
    public static void add(Person per) {
        //使用工具类获取数据库连接，try()异常，自动关闭数据库连接
        try (Connection conn = JDBCUtils.getConnection()) {
//            使用占位符定义添加sql语句，字符串中的【?】为占位符，具体的值通过PreparedStatement对象设置
//          使用insert ignore语法插入数据时，如果发生主键或者唯一键冲突，则忽略这条插入的数据。
            String insSql = "INSERT ignore INTO sciperson (tno,tpassword,tname,tuni,tcompetent,tsex) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insSql);//实例化PreparedStatement对象
            pstmt.setString(1, per.getTno());//设置sql语句中第一个“?”的值
            pstmt.setString(2, per.getTpassword());//设置sql语句中第二个“?”的值
            pstmt.setString(3, per.getTname());//设置sql语句中第二个“?”的值
            pstmt.setString(4, per.getTuni());//设置sql语句中第二个“?”的值
            pstmt.setString(5, per.getTcompetent());//设置sql语句中第二个“?”的值
            pstmt.setString(6, per.getTsex());//设置sql语句中第二个“?”的值
            int i = pstmt.executeUpdate();//按指定sql语句，执行数据库更新操作
            System.out.println(i + "条数据添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改操作
     *
     * @param per
     */
    public static void update(Person per) {
        //使用工具类获取数据库连接，try()异常，自动关闭数据库连接
        try (Connection conn = JDBCUtils.getConnection()) {
            //使用占位符定义添加sql语句，字符串中的【?】为占位符，具体的值通过PreparedStatement对象设置
            String sql = "update sciperson set tpassword=?,tname = ?,tuni=?,tcompetent=?,tsex=? where tno=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);//实例化PreparedStatement对象
//            按照sql中间的？的顺序
            pstmt.setString(1, per.getTpassword());//设置sql语句中第一个“?”的值
            pstmt.setString(2, per.getTname());//设置sql语句中第二个“?”的值
            pstmt.setString(3, per.getTuni());
            pstmt.setString(4, per.getTcompetent());
            pstmt.setString(5, per.getTsex());
            pstmt.setString(6, per.getTno());
            int i = pstmt.executeUpdate();//按指定sql语句，执行数据库更新操作
            System.out.println(i + "条数据修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void del(String tno) {
        //使用工具类获取数据库连接，tyr()异常，自动关闭数据库连接
        try (Connection conn = JDBCUtils.getConnection()) {
            //使用占位符定义添加sql语句，字符串中的【?】为占位符，具体的值通过PreparedStatement对象设置
            String insSql = "delete from sciperson AS aa where aa.tno=?";
            PreparedStatement pstmt = conn.prepareStatement(insSql);//实例化PreparedStatement对象
            pstmt.setString(1, tno);//设置sql语句中第一个“?”的值
            int i = pstmt.executeUpdate();//按指定sql语句，执行数据库更新操作
            System.out.println(i + "条数据删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义根据数据表主键查询数据的方法
     *
     * @param tno tno为主键
     */
    public void findByTno(String tno) {
        //使用工具类获取数据库连接，try()异常，自动关闭数据库连接
        // idea快速生成try..catch，快捷键crtl+alt+t
        try (Connection conn = JDBCUtils.getConnection()) {
            //使用占位符定义添加sql语句，字符串中的【?】为占位符，具体的值通过PreparedStatement对象设置
            String sql = "SELECT sciperson.tno,tname,tsex,tuni,scigain.gname,scigain.gtype  FROM `sciperson`join scigain " +
                    "on sciperson.tno = scigain.tno WHERE sciperson.tno = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);//实例化PreparedStatement对象
            pstmt.setString(1, tno);//设置sql语句中第一个“?”的值
            ResultSet rs = pstmt.executeQuery();//按行数据库查询操作
            while (rs.next()) {//判断数据集是否有数据
                //获取数据集中每个字段的值，实例化科研人员信息对象
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
