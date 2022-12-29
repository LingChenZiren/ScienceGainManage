package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.example.demo.JDBCController.JDBCUtils;
import com.example.demo.pojo.Gain;
import com.example.demo.pojo.Person;

public class PersonService {
    private static ArrayList<Person> list = new ArrayList();
/*
    //    数据初始化
    {
        list.add(new Person("400", "ufaoksdujfio", "护甲修", "吉首大学", "教授", "3907", "男"));
        list.add(new Person("401", "jsfkdj", "狐猴", "湖南大学", "副教授", "7809", "女"));
        list.add(new Person("402", "ksduhjkah", "水果糖", "中南大学", "教授", "0983", "男"));
    }*/

    public ArrayList<Person> getPerson() {
        return (ArrayList<Person>) list;
    }

    /**
     * 按照工号或者姓名查找科研人员
     *
     * @param key 传入的工号或姓名
     * @return results
     */
    public ArrayList<Person> findPerson(String key) {
        ArrayList<Person> results = new ArrayList<>();
        for (Person person : list) {
            if (person.getTno().contains(key) || person.getTname().contains(key)) {
                results.add(person);
            }
        }
        return results;
    }


    /**
     * 精确查找 由工号查询
     *
     * @param num 待查询的工号
     * @return person or null
     */
    public boolean findByTno(String num) {
        boolean result = false;
        try (Connection conn = JDBCUtils.getConnection()) {//创建数据库连接
            String sql = "SELECT sciperson.tno,tname,tsex,tuni,scigain.gname,scigain.gtype  FROM `sciperson`join scigain " +
                    "on sciperson.tno = scigain.tno WHERE sciperson.tno = ?";//定义查询所有数据sql语句
            PreparedStatement ps = conn.prepareStatement(sql);//填充sql语句
            ps.setString(1, num);
            ResultSet rs = ps.executeQuery();//执行sql语句
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除指定工号科研人员信息
     *
     * @param tno 要删除科研人员对象的学号
     * @return 返回整型，值为0删除失败
     */
    //
    public int del(String tno) {
        int result = 0;
        try (Connection conn = JDBCUtils.getConnection()) {//连接数据库
            String sql = "delete from sciperson where tno=?";//定义删除指定学号sql语句
            PreparedStatement pstm = conn.prepareStatement(sql);//填充sql语句
            pstm.setString(1, tno);//设置第一个?的值
            result = pstm.executeUpdate();//执行sql语句
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;//返回删除结果
        }
    }

    /**
     * 定义查询数据表所有数据的方法
     *
     * @return 返回实体类集合
     */
    public ArrayList<Person> findAll() {
        ArrayList<Person> person = new ArrayList<>();
        ArrayList<Gain> gains = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection()) {//创建数据库连接
            String sql = "select * from sciperson join scigain on sciperson.tno = scigain.tno order by sciperson.tno";//定义查询所有数据sql语句
            PreparedStatement ps = conn.prepareStatement(sql);//填充sql语句
            ResultSet rs = ps.executeQuery();//执行sql语句
            while (rs.next()) {//判断数据集是否有数据
                //获取数据集中每个字段的值，实例化科研人员信息对象
                Person person2 = new Person(rs.getString(1),
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6));
                person.add(person2);
                Gain gain = new Gain(rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                person2.setGname(gain.getGname());
                person2.setGtype(gain.getGtype());
                gains.add(gain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*for(Person temp : person){
            for(Gain i : gains){
               if(temp.getTno() == i.getTno()){
                   temp.getGains().add(i);
               }
            }
        }*/
        /*for (Person person1 : person) {
            System.out.println("性别：" + person1.getTsex());
            System.out.println("姓名：" + person1.getTname());
        }*/
        return person;
    }

    /**
     * 查询按钮中间使用的find方法 是利用工号和姓名进行模糊查找
     *
     * @param key 输入的值
     * @return
     */
    public List<Person> find(String key) {

        List<Person> person = new ArrayList<>();
        List<Gain> gains = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection()) {//创建数据库连接
            StringBuilder conditions = new StringBuilder();//定义查询条件字符串
            if ("".equals(key)) {//未输入查询关键字，显示所有数据
                conditions = conditions.append("1=1");
            } else {//拼接按工号、姓名模糊查询的条件字符串
                conditions.append("sciperson.tno like '%")
                        .append(key)
                        .append("%' or tname like '%")
                        .append(key).append("%'");
            }
            //定义查询所有数据sql语句
            String sql = "select * from sciperson join scigain on sciperson.tno = scigain.tno where " + conditions.toString();
            PreparedStatement ps = conn.prepareStatement(sql);//填充sql语句

            ResultSet rs = ps.executeQuery();//执行sql语句
            while (rs.next()) {//判断数据集是否有数据
                //获取数据集中每个字段的值，实例化科研人员信息对象
                Person person2 = new Person
                        (rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6));
                person.add(person2);//将科研人员对象添加至集合
                Gain gain2 = new Gain(rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
                gains.add(gain2);
                person2.setGname(gain2.getGname());
                person2.setGtype(gain2.getGtype());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*for (Person person1:person){
            for (Gain j: gains){
                if (person1.getTno() == j.getTno()){
                    person1.getGains().add(j);
                }
            }
        }*/
        return person;
    }

    public int add(Person person) {
        int result = 0;
        //使用自定义工具类获取数据库连接，try()异常，自动关闭数据库连接
        try (Connection conn = JDBCUtils.getConnection()) {
            //使用占位符定义添加sql语句，字符串中的【?】为占位符，具体的值通过PreparedStatement对象设置
            String sql = "insert into sciperson (tno, tname, tuni,tsex) values (?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);//实例化PreparedStatement对象
            pstmt.setString(1, person.getTno());//设置sql语句中第一个“?”的值
            pstmt.setString(2, person.getTname());//设置sql语句中第二个“?”的值
            pstmt.setString(3, person.getTuni());
            pstmt.setString(4, person.getTsex());
            result = pstmt.executeUpdate();//按指定sql语句，执行数据库更新操作
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Person person) {
        int result = 0;
        //使用自定义工具类获取数据库连接，try()异常，自动关闭数据库连接
        try (Connection conn = JDBCUtils.getConnection()) {
            //使用占位符定义添加sql语句，字符串中的【?】为占位符，具体的值通过PreparedStatement对象设置
            String sql = "update sciperson set tname = ?,tsex = ?,tuni = ? where tno = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);//实例化PreparedStatement对象
            pstmt.setString(1, person.getTname());//设置sql语句中第二个“?”的值
            pstmt.setString(2, person.getTsex());
            pstmt.setString(3, person.getTuni());
            pstmt.setString(4, person.getTno());//设置sql语句中第一个“?”的值
            result = pstmt.executeUpdate();//按指定sql语句，执行数据库更新操作
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
