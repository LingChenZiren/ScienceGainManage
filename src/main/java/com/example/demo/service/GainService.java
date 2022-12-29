package com.example.demo.service;

import com.example.demo.JDBCController.JDBCUtils;
import com.example.demo.pojo.Gain;
import com.example.demo.pojo.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GainService {
    public int updatetwo(Gain gain,Person person) {
        int result = 0;
        //使用自定义工具类获取数据库连接，try()异常，自动关闭数据库连接
        try (Connection conn = JDBCUtils.getConnection()) {
            //使用占位符定义添加sql语句，字符串中的【?】为占位符，具体的值通过PreparedStatement对象设置
            String sql = "update scigain set gtype = ? where scigain.gname = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);//实例化PreparedStatement对象
            pstmt.setString(2, gain.getGname());//设置sql语句中第一个“?”的值
            pstmt.setString(1, gain.getGtype());//设置sql语句中第二个“?”的值
//            pstmt.setString(3, person.getTno());//设置sql语句中第二个“?”的值

            //pstmt.setString(3, person.getGname());//设置sql语句中第二个“?”的值
//            pstmt.setString(4, person.getGtype());//设置sql语句中第二个“?”的值
            result = pstmt.executeUpdate();//按指定sql语句，执行数据库更新操作
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int addtwo(Gain gain) {
        int result2 = 0;
        //使用自定义工具类获取数据库连接，try()异常，自动关闭数据库连接
        try (Connection conn = JDBCUtils.getConnection()) {
            //使用占位符定义添加sql语句，字符串中的【?】为占位符，具体的值通过PreparedStatement对象设置
            String issql = "insert into scigain(gname,gtype,tno) values (?,?,?);";
            PreparedStatement pstm = conn.prepareStatement(issql);//实例化PreparedStatement对象
            pstm.setString(1, gain.getGname());
            pstm.setString(2, gain.getGtype());
            pstm.setString(3, gain.getTno());
            result2 = pstm.executeUpdate();//按指定sql语句，执行数据库更新操作
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result2;
    }
}
