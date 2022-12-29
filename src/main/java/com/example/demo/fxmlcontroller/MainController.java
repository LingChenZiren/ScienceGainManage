package com.example.demo.fxmlcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public BorderPane mainPane;
    @FXML
    private Label exit;

    /**
     * 退出系统询问
     * @param mouseEvent
     */
    public void exitsys(MouseEvent mouseEvent) {
        //使用系统确认框询问
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("退出询问");
        alert.setHeaderText("您确定要退出系统吗？");
//      设置对话框的内容文本
//      alert.setContentText("尊敬的用户，你真的要卸载我吗？");
//      显示对话框，并等待按钮返回
        Optional<ButtonType> result = alert.showAndWait();
//      判断返回的按钮类型是确定还是取消，再据此分别进一步处理
        if (result.get() == ButtonType.OK) { // 单击了确定按钮OK
//      lblExit为成员变量，即退出组件的fx:id
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.controllerMap.put("MainController",this);
        //System.out.println("测试：" + this);
    }
}
