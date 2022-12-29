package com.example.demo.fxmlcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LeftMenu01Controller {
    /**
     * 功能菜单点击事件
     * @param fxmlfile 要加载的界面文件
     * @throws IOException
     */
    public void loadFxml(String fxmlfile) throws IOException {

        //获取界面文件
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlfile));
        //获取界面中的根面板容器
        Pane cmdPane = (Pane) fxmlLoader.load();
        //在子窗体中获取父窗体的控制器，不将此变量定义在方法体外，因为子控制器先执行，父控制器后执行;
        //System.out.println("map中的：" + ControllerManager.controllerMap.get("MainController"));
        MainController mainController = (MainController) ControllerManager.controllerMap.get("MainController");
        //设置主界面上下左右中布局的中间布局的内容为上述指定界面
        mainController.mainPane.setCenter(cmdPane);
    }

    public void itemClickedTwo(MouseEvent mouseEvent) throws IOException {
        loadFxml("/com/example/demo/fxml/PersonMain.fxml");
    }


    public void itemClicked(MouseEvent mouseEvent) throws IOException {
        loadFxml("/com/example/demo/fxml/hello-view.fxml");
    }
}
/*
    public void itemClicked(MouseEvent mouseEvent) throws IOException {
//      加载界面fxml文件，注意路径第一个“/”代表项目输出根目录的classes目录
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("/com/example/demo/fxml/hello-view.fxml"));
//      设置场景使用界面文件,以及场景大小
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
//      设置窗口标题
        stage.setTitle("request");
//      设置舞台（窗体）使用的场景
        stage.setScene(scene);
//        设置舞台（窗体）最大化
//        stage.setMaximized(true);
//        设置舞台（窗体）无标题栏、关闭等按钮
//        stage.initStyle(StageStyle.TRANSPARENT);
//        设置当前窗体不关闭不能使用其它窗体
        stage.initModality(Modality.APPLICATION_MODAL);
//        设置窗口图标
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/pictures/logo_long.png"))));
//        显示舞台（窗体）
        stage.show();
    }

    public void itemClickedTwo(MouseEvent mouseEvent) throws IOException {
//获取界面文件
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/PersonMain.fxml"));
//获取界面中的根面板容器
        Pane cmdPane = (Pane) fxmlLoader.load();
//在子窗体中获取父窗体的控制器，不将此变量定义在方法体外，因为子控制器先执行，父控制器后执行
        MainController mainController = (MainController) ControllerManager.controllerMap.get("mainController");
//设置主界面上下左右中布局的中间布局的内容为上述指定界面
        mainController.mainPane.setCenter(cmdPane);
    }
}*/
