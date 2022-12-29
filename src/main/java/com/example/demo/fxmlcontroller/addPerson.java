package com.example.demo.fxmlcontroller;

import com.example.demo.service.PersonService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;

public class addPerson implements Initializable {
    @FXML
    private Button addperson;

    @FXML
    private Label msgtno;

    @FXML
    private TextField txttname;

    @FXML
    private RadioButton txttsexman;

    @FXML
    private Label msgtsex;

    @FXML
    private TextField txttno;

    @FXML
    private Label msgtname;

    @FXML
    private Label msgtuni;

    @FXML
    private TextField txttuni;

    @FXML
    private RadioButton txttsexwoman;

    @FXML
    public void addClick(ActionEvent actionEvent) {

    }

    public boolean isadd = true;
    PersonService per2 = new PersonService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        学号文本框失去焦点事件
        txttno.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
//                数据改变
                if (oldValue && !newValue) {
                    String tno = msgtno.getText();
                    if (tno.length() == 0) {
                        msgtno.setText("工号不能为空！");
                        txttno.requestFocus();
                        isadd = false;
                    } else if (per2.findByTno(tno) == false) {
                        msgtno.setText("工号已存在！");
                        txttno.requestFocus();
                        isadd = false;
                    } else {
                        msgtno.setText("");
                        isadd = true;
                    }
                }
            }
        });

//        姓名文本框失去焦点事件
        txttname.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
//                数据改变
                if (oldValue && !newValue) {
                    String tname = msgtname.getText();
                    if (tname.length() == 0) {
                        msgtname.setText("工号不能为空！");
                        txttname.requestFocus();
                        isadd = false;
                    } else {
                        msgtname.setText("");
                        isadd = true;
                    }
                }
            }
        });

//       学校文本框失去焦点事件
        txttuni.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
//                数据改变
                if (oldValue && !newValue) {
                    String tuni = msgtuni.getText();
                    if (tuni.length() == 0) {
                        msgtuni.setText("工号不能为空！");
                        txttuni.requestFocus();
                        isadd = false;
                    } else {
                        msgtuni.setText("");
                        isadd = true;
                    }
                }
            }
        });
    }

    public void btnOk(ActionEvent actionEvent) {
        if (isadd) {
            String tno = txttno.getText();
            String tname = txttname.getText();
            String tuni = txttuni.getText();
//            Person per3 = new Person(tno, tname,tsex, tuni);
//            per2.addPerson(per3);
            Stage stage = (Stage) txttno.getScene().getWindow();
            stage.close();
            PersonMainController pc = (PersonMainController) ControllerManager.controllerMap.get("MainController");
            pc.initTable(per2.getPerson());
        }
    }

}
