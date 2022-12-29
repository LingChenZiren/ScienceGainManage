package com.example.demo.fxmlcontroller;

import com.example.demo.pojo.Gain;
import com.example.demo.pojo.Person;
import com.example.demo.service.GainService;
import com.example.demo.service.PersonService;
import com.sun.javafx.sg.prism.NGPerspectiveCamera;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**编辑页面控制器，实现锁定工号不能修改
 */
public class EditPersonController implements Initializable {
    public TextField tno;
    public TextField tname;
    public RadioButton man;
    public ToggleGroup tsex;
    public RadioButton woman;
    public TextField tuni;
    public TextField gtype;
    public TextField gname;
    public Person person4;

    PersonService personService = new PersonService();

    //界面初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PersonMainController personMainController = (PersonMainController) ControllerManager.controllerMap.get("PersonMainController");
        person4 = personMainController.person;

        tno.setText(person4.getTno());//设置学号文本框的值
        tname.setText(person4.getTname());//设置姓名文本框的值
        woman.setSelected(true);//设置性别单选框的值
        tuni.setText(person4.getTuni());//设置手机号码输入框的值
        gname.setText(person4.getGname());//设置手机号码输入框的值
        gtype.setText(person4.getGtype());//设置手机号码输入框的值

        /*//姓名输入框失去焦点事件
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
                }
            }
        });*/
        /*//学校输入框失去焦点事件
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
                }
            }
        });*/
    }

    /**
     * 完成按钮方法
     * @param actionEvent 点击
     */
    public void btnOk(ActionEvent actionEvent) {
        String strtno = tno.getText();//获取用户输入的工号
        String strtname = tname.getText();//获取用户输入的姓名
        String strsex = tsex.getSelectedToggle().getUserData().toString();//获取用户选择的性别
        String strtuni = tuni.getText();
        String strgname = gname.getText();
        String strgtype = gtype.getText();
        Person p1 = new Person(strtno, strtname,strsex,strtuni);
        Gain g1 = new Gain(strgname,strgtype,strtno);

        if (tno.getLength() == 0){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("姓名不能为空");
            alert.show();
            tno.requestFocus();
            return;
        }

        int result = new PersonService().update(p1);
        int result2 = new GainService().updatetwo(g1,person4);
        if (result > 0 && result2 > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("数据修改成功！");
            alert.show();

            Stage stage = (Stage) tno.getScene().getWindow();//获取当前窗口
            stage.close();//关闭当前窗口

            PersonMainController sc =
                    (PersonMainController) ControllerManager.controllerMap.get("PersonMainController");//获取科研人员信息管理主窗口控制器
            sc.initTableView();//调用控制器中的方法刷新数据表格
        }
    }
}

