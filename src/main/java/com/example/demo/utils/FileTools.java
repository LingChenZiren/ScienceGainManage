package com.example.demo.utils;

import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.text.ParseException;
import java.util.List;

public class FileTools {
    /**
     * 写Excel文件
     *
     * @param fileName 写入excel的文件名
     * @param cls      写入Excel数据对应的实体类的Class对象
     * @param list     写入的数据集合
     * @param <T>      泛型，一般类型为要写入的实体类
     * @return 写入的条数
     * @throws ParseException
     */
    public static <T> int writeExcel(String fileName, Class<T> cls, List<T> list) throws ParseException {
        File destfile = new File(fileName);//目标文件
        EasyExcel.write(destfile, cls)
                .sheet()
                .doWrite(list);
        return list.size();
    }
}
