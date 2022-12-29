package com.example.demo.fxmlcontroller;/*
public class StuInfoMainController implements Initializable {
    public TableView tabStuinfo;//数据表格组件
    public TableColumn sno;//列组件
    public TableColumn name;
    public TableColumn age;
    public TableColumn sex;
    public TableColumn tel;
    public TableColumn photo;
    public Pagination pageTable;//分页组件
    public TextField txtKey;//搜索文本框
    StuInfoService service=new StuInfoService();//定义数据操作对象
    public List<StuInfo> stuInfos;//定义存放数据库数据表数据的集合
    public int total;//定义数据库中数据表的总记录数
    public int pagesize=10;//数据表格组件每页显示的条数
    public int pagecount;//数据总页数


    //加载指定页数据至数据表格
    public void loadCurPageData(int pageIndex) {
        //调用自定义工具类，得到指定页数据集合
        List<StuInfo> subList= List<StuInfo>)ListTools.pagingList(stuInfos,10,pageIndex);

        //设置数据表格的数据源为上述分页集合
        ObservableList<StuInfo> data = FXCollections.observableArrayList(subList);
        //设置每一列的值，前面的【sno】等为界面数据表格中列组件的fx:id，后面的【sno】等为StuInfo类中的成员变量名称
        sno.setCellValueFactory(new PropertyValueFactory<>("sno"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        // 设置自定义组件，用于显示图片
        photo.setCellFactory(new Callback<TableColumn<StuInfo,String>, TableCell<StuInfo, String>>() {
            @Override
            public TableCell<StuInfo, String> call(TableColumn<StuInfo, String> stuInfoStringTableColumn) {
                TableCell<StuInfo, String> cell = new TableCell<StuInfo, String>(){
                    @Override
                    protected void updateItem(String s, boolean b) {//s为单元格的值
                        super.updateItem(s, b);
                        if (b == false && s != null) {
                            HBox box = new HBox();
                            box.setAlignment(Pos.CENTER);
                            Image img=new Image(getClass().getResourceAsStream("/yym/jsu/images/"+s));//设置图片
                            ImageView imageView=new ImageView(img);
                            imageView.setFitWidth(32);
                            imageView.setFitHeight(32);
                            box.getChildren().add(imageView);
                            this.setGraphic(box);
                        }
                    }
                };
                return cell;
            }
        });
        // 绑定TableView（tbStudent）的数据源
        tabStuinfo.setItems(data);
    }

    //装数据表格组件加载数据
    public void initTableView() {
        //根据搜索文本框的值获取数据库的数据
        stuInfos = service.find(txtKey.getText());
        total=stuInfos.size();//获取总记录条数
        pagecount=(total+(pagesize-1))/pagesize;//得到总页数
        pageTable.setPageCount(pagecount);//设置分页组件的总页数
        //绑定分页组件中分页按钮的点击
        pageTable.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pagenum) {//点击分页按钮的回调函数
                loadCurPageData(pagenum);
                return tabStuinfo;//返回装载当前页数据的tableview
            }
        });
    }

    //界面初始化代码
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
    }
    public void btnFind(ActionEvent actionEvent) {//查询按钮事件代码
        initTableView();
    }
    public void btnDel(ActionEvent actionEvent) {//右键删除菜单事件代码
    }

    public void btnAdd(ActionEvent actionEvent) {//添加按钮事件代码
    }

    public void btnEdit(ActionEvent actionEvent) {//右键编辑菜单事件代码
    }*/

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.print("监听到窗口关闭");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
