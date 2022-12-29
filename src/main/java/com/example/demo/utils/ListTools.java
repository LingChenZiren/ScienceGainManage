package com.example.demo.utils;

import com.example.demo.pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class ListTools {
    /**
     * @param pageSize 每页显示的数量
     * @param pageNum  当前页码
     * @Description: subList 对集合进行分页
     */
    public static List<?> pagingList(List<?> list, int pageSize, int pageNum) {
        int count = list.size(); // 总记录数
        // 计算总页数
        int pages = (count + (pageSize - 1)) / pageSize;//总页数 ;
        // 起始位置
        int start = pageNum * pageSize;
        // 终止位置
        int end = (pageNum + 1) * pageSize > count ? count : (pageNum + 1) * pageSize;
        return list.subList(start, end);
    }


}
