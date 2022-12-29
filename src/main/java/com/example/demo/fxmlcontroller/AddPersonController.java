package com.example.demo.fxmlcontroller;

import com.example.demo.pojo.Gain;
import com.example.demo.pojo.Person;
import com.example.demo.service.GainService;
import com.example.demo.service.PersonService;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * 实现Initializable接口，重写initialize方法，实现界面初始化
 * 添加窗口控制器
 */
public class AddPersonController implements Initializable {
    public TextField tno;
    public TextField tname;
    public RadioButton man;
    public ToggleGroup tsex;
    public RadioButton woman;
    public TextField tuni;
    public TextField gname;
    public TextField gtype;

    /**
     * 界面初始化
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      /*  //工号输入框失去焦点事件
        tno.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                //数据改变
                if (oldValue && !newValue) {
                    //数据库查询学号是否存在
                    if (new PersonService().findByTno(tno.getText())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("工号已存在，请重新输入！");
                        alert.show();
                        tno.requestFocus();
                    }
                    if (tno.getLength() == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("工号不能为空");
                        alert.show();
                        tno.requestFocus();
                    }
                }
            }
        });*/
       /* //姓名输入框失去焦点事件
        tname.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                //数据改变
                if (oldValue && !newValue) {
                    if (tname.getLength() == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("姓名不能为空");
                        alert.show();
                        tname.requestFocus();
                    }
//                        数据库查询学号是否存在
                        if (new PersonService().findByTno(tname.getText())) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("学号已存在，请重新输入！");
                            alert.show();
                            tname.requestFocus();
                        }*//*
                }
            }
        });*/
       /* //学校输入框失去焦点事件
        tuni.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                //数据改变
                if (oldValue && !newValue) {
                    if (tuni.getLength() == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("学校不能为空");
                        alert.show();
                        tuni.requestFocus();
                    }
//                        数据库查询学号是否存在
                        if (new PersonService().findByTno(tno.getText())) {
                            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("学号已存在，请重新输入！");
                            alert.show();
                            tno.requestFocus();//
                        }*//*
                }
            }
        });*/
    }

    /**
     * 添加按钮事件，设置界面文件中添加按钮的属性onAction="#btnOk"
     *
     * @param actionEvent
     */
    public void btnOk(ActionEvent actionEvent) {

        String strtno = tno.getText();//获取用户输入的工号
        String strtname = tname.getText();//获取用户输入的姓名
        String strtsex = tsex.getSelectedToggle().getUserData().toString();//获取用户选择的性别
        String strtuni = tuni.getText();
        String strgname = gname.getText();
        String strgtype = gtype.getText();
        Person p1 = new Person(strtno, strtname, strtsex, strtuni);
        Gain g1 = new Gain(strgname, strgtype, strtno);
        /*Tooltip tooltip = new Tooltip("请勿重复");
        tooltip.setFont(Font.font(30));*/

        if (new PersonService().findByTno(tno.getText())){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("工号已存在，请重新输入！");
            alert.show();
            tno.requestFocus();
            return;
        }

        if (tno.getLength() == 0){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("工号不能为空");
            alert.show();
            tno.requestFocus();
            return;
        }

        if (tname.getLength() == 0){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("姓名不能为空");
            alert.show();
            tno.requestFocus();
            return;
        }

        int result = new PersonService().add(p1);
        int result2 = new GainService().addtwo(g1);
        if (result > 0 && result2 > 0) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("数据添加成功！");
            alert.show();

            Stage stage = (Stage) tno.getScene().getWindow();//获取当前窗口
            stage.close();//关闭当前窗口

            PersonMainController sc =
                    (PersonMainController) ControllerManager.controllerMap.get("PersonMainController");//获取科研人员信息管理主窗口控制器
            sc.initTableView();//调用控制器中的方法刷新数据表格

        }
    }
}
