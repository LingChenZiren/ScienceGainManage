package com.example.demo.pojo;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ContentRowHeight(20)//
@HeadRowHeight(20)
@ColumnWidth(20)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {


    private String tno;
    private String tpassword;
    private String tname;
    private String tuni;
    private String tcompetent;
    private String tsex;
    private String gname;
    private String gtype;

    //private ArrayList<Gain> Gains = new ArrayList<>();


    public Person(String tno, String tpassword, String tname, String tuni, String tcompetent, String tsex) {
        this.tno = tno;
        this.tpassword = tpassword;
        this.tname = tname;
        this.tuni = tuni;
        this.tcompetent = tcompetent;
        this.tsex = tsex;
    }

    public Person(String tno, String tname, String tsex, String tuni) {
        this.tno = tno;
        this.tname = tname;
        this.tuni = tuni;
        this.tsex = tsex;
    }
}