package com.example.demo.service;

import com.example.demo.pojo.Gain;
import com.example.demo.pojo.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GainServiceTest {
    GainService gainService = new GainService();
    Gain gain = new Gain("成果", "专利","111");
    Person person = new Person("111", "fhksdjkhf", "教师", "吉首大学", "教授", "男","成果","专利");

    @Test
    void updatetwo() {
        int a = gainService.updatetwo(gain, person);
        System.out.println("修改" + a + "个数据");
    }
}