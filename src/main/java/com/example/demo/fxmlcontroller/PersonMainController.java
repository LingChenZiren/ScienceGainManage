package com.example.demo.fxmlcontroller;

import com.example.demo.pojo.Gain;
import com.example.demo.pojo.Person;
import com.example.demo.service.PersonService;
import com.example.demo.utils.FileTools;
import com.example.demo.utils.ListTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PersonMainController implements Initializable {
    public TableColumn tbcTno;
    public TableColumn tbcTname;
    public TableColumn tbcTsex;
    public TableColumn tbcTuni;
    public TableColumn tbcGname;
    public TableColumn tbcGType;
    public TableView tbPerson;
    public TextField txtKey;
    public Pagination pageTable;
    public List<Person> personlist;//定义存放数据库数据表数据的集合
    public int total;//定义数据库中数据表的总记录数
    public int pagesize = 10;//数据表格组件每页显示的条数
    public int pagecount;//数据总页数

    public Person person;//定义科研人员信息对象


    PersonService per = new PersonService();

    /**
     * 分页方法1
     *
     * @param pageIndex
     * @return
     */
    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        /*int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
            VBox element = new VBox();
            Hyperlink link = new Hyperlink("Item " + (i+1));
            link.setVisited(true);
            Label text = new Label("Search results\nfor "+ link.getText());
            element.getChildren().addAll(link, text);
            box.getChildren().add(element);
        }*/
        return box;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableView();
        //将本控制器加入自定义的控制器管理集合，方便窗体间传递消息
        ControllerManager.controllerMap.put("PersonMainController", this);
//        System.out.println(this);
    }

    /**
     * 装数据表格组件加载数据
     */
    public void initTableView() {
        //根据搜索文本框的值获取数据库的数据
        //personlist = per.find(txtKey.getText());
        personlist = per.findAll();
        total = personlist.size();//获取总记录条数
        pagecount = (total + (pagesize - 1)) / pagesize;//得到总页数
        pageTable.setPageCount(pagecount);//设置分页组件的总页数

//      调用数据操作类获取数据加载数据表格（TableView）
//        initTable(per.getPerson());
        //initTable(per.findAll());
        initTable((ArrayList<Person>) personlist);

        // 初始化分页插件
        pageTable.setCurrentPageIndex(0);//设置起始页
        //绑定分页组件中分页按钮的点击
        pageTable.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {//点击分页按钮的回调函数
                loadCurPageData(pageIndex);//获取当前页数据
                return createPage(pageIndex);//返回装载当前页数据的tableview
            }
        });
    }

    public void initTable(ArrayList<Person> list) {
        /*for(int i = 0; i < list.size(); i++){
            System.out.println("学校：" + list.get(i).getTuni());
            System.out.println("性别：" + list.get(i).getTsex());
        }*/
//      设置数据表格源
        ObservableList<Person> data = FXCollections.observableArrayList(list);
//      设置每一列的值，【tbTno】等为界面数据表格中列的fx:id，【Tno】等为person类中的成员变量名称
        tbcTno.setCellValueFactory(new PropertyValueFactory<>("Tno"));
        tbcTname.setCellValueFactory(new PropertyValueFactory<>("tname"));
        tbcTsex.setCellValueFactory(new PropertyValueFactory<>("tsex"));
        tbcTuni.setCellValueFactory(new PropertyValueFactory<>("tuni"));
        tbcGname.setCellValueFactory(new PropertyValueFactory<>("gname"));
        tbcGType.setCellValueFactory(new PropertyValueFactory<>("gtype"));
//      设置TableView（tbPerson）的数据源
        tbPerson.setItems(data);
//        System.out.println(tbPerson);
//        tbPerson.setEditable(true);//设置数据表格可编辑

    }

    /**
     * 查询按钮点击方法 点击即可进行查询操作
     *
     * @param actionEvent
     */
    public void findClick(ActionEvent actionEvent) {
//      initTable方法加载指定科研人员对象List集合数据到数据表格
//      findStu方法自定义数据操作类按查询成绩方法
//      txtKey.getText()获取文本框输入的值
        List<Person> temp = new ArrayList<>();
//        initTable(per.findPerson(txtKey.getText()));
        temp = per.find(txtKey.getText());
        initTable((ArrayList<Person>) temp);
    }

    public void delClick(ActionEvent actionEvent) {
        //获取选定行数据
        Person s1 = (Person) tbPerson.getSelectionModel().getSelectedItem();
//      使用系统确认框询问
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("您确定要删除工号为【" + s1.getTno() + "】的 信息吗？");
//      设置对话框的内容文本
//      alert.setContentText("尊敬的用户，你真的要卸载我吗？");
//      显示对话框，并等待按钮返回
        Optional<ButtonType> result = alert.showAndWait();
//      判断返回的按钮类型是确定还是取消，再据此分别进一步处理
        if (result.get() == ButtonType.OK) { // 单击了确定按钮OK
//      调用数据操作类删除科研人员信息的方法
            per.del(s1.getTno());
//      重新加载数据表格视图数据
            initTable(per.getPerson());
        }
    }

    /**
     * 添加按钮事件代码
     *
     * @param actionEvent
     * @throws IOException
     */
    public void AddClick(ActionEvent actionEvent) throws IOException {
        //获取界面文件
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/AddPerson.fxml"));
        //获取界面中的根面板容器
//        Pane cmdPane = (Pane) fxmlLoader.load();
//在子窗体中获取父窗体的控制器，不将此变量定义在方法体外，因为子控制器先执行，父控制器后执行
//        MainController mainController = (MainController) ControllerManager.controllerMap.get("MainController");
//设置主界面上下左右中布局的中间布局的内容为上述指定界面
//        mainController.mainPane.setCenter(cmdPane);
        Scene scene1 = new Scene(fxmlLoader.load());//加载界面到Scene场景对象
        Stage stage = new Stage();//创建Stage窗体对象
        stage.setScene(scene1);//设置窗体的场景
        // stage.initStyle(StageStyle.TRANSPARENT);
        //设置窗口图标
//        System.out.println("png："+ getClass().getResource("/src/main/resources/pictures/add.png"));
//        System.out.println("图片获取：" + String.valueOf(getClass().getResource("/src/main/resources/pictures/add.png")));
       /* System.out.println("图片：" + getClass().getResource("/src/main/resources/pictures/add.png"));
        Image image = new Image(String.valueOf(getClass().getResource("/src/main/resources/pictures/add.png")));*/
        /*System.out.println("图片：" + image);
        stage.getIcons().add(image);*/
        //设置窗口标题
        stage.setTitle("添加数据");
        //设置窗体不关闭不能使用其它窗体
        stage.initModality(Modality.APPLICATION_MODAL);
        //设置窗口大小不能改变
        stage.setResizable(false);
        stage.show();//显示窗体
    }


    /**
     * 页面切换调用该方法
     *
     * @param pageIndex 当前页码
     * @return void
     */
    public void loadCurPageData(int pageIndex) {
        //调用自定义工具类，得到指定页数据集合
        List<Person> subList = (List<Person>) ListTools.pagingList(personlist, 10, pageIndex);
        ObservableList<Person> data2 = FXCollections.observableArrayList(subList);

        ArrayList<Person> perlist = per.findAll();//获取数据
       /* int total = perlist.size();//总条数
        int pagesize = 10;//每页显示条数
        int pagecount = (total + (pagesize - 1)) / pagesize;//总页数
        //由于余数最小为1，那么先加上最大的余数（pagesize-1）后，只要totalrow/pagesize余数不为0，（totalpage+（pagesize-1））/pagesize的结果一定会比totalrow/pagesize大1；否则结果相等；
        ArrayList<Person> pstulist = new ArrayList<>();//定义装载当前页数据的集合
        for (int i = pageIndex * pagesize; i < perlist.size() && i < (pageIndex + 1) * pagesize; i++) {
            pstulist.add(perlist.get(i));
        }
        //设置数据表格源
        ObservableList<Person> data = FXCollections.observableArrayList(pstulist);*/
//        设置每一列的值，【tbTno】等为界面数据表格中列的fx:id，【Tno】等为person类中的成员变量名称
        tbcTno.setCellValueFactory(new PropertyValueFactory<>("Tno"));
        tbcTname.setCellValueFactory(new PropertyValueFactory<>("tname"));
        tbcTsex.setCellValueFactory(new PropertyValueFactory<>("tsex"));

        tbcTuni.setCellValueFactory(new PropertyValueFactory<>("tuni"));
//      设置TableView（tbPerson）的数据源
        tbPerson.setItems(data2);
    }

    /**
     * 右键删除菜单事件代码
     *
     * @param actionEvent
     */
    public void btnDel(ActionEvent actionEvent) {
        //获取选定行数据
        Person p1 = (Person) tbPerson.getSelectionModel().getSelectedItem();
        //使用系统确认框询问
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("您确定要删除工号为【" + p1.getTno() + "】的数据吗？");
        // 显示对话框，并等待按钮返回
        Optional<ButtonType> result = alert.showAndWait();
        // 判断返回的按钮类型是确定还是取消，再据此分别进一步处理
        if (result.get() == ButtonType.OK) { // 单击了确定按钮OK
            per.del(p1.getTno());//调用数据操作类删除科研人员成绩的方法
            int pagenum = pageTable.getCurrentPageIndex();//获取当前页码
            initTableView(); //重新加载数据表格视图数据
            pageTable.setCurrentPageIndex(pagenum);//设置当前页码
        }
    }

    /**
     * 右键编辑菜单事件代码
     *
     * @param actionEvent
     * @throws IOException
     */
    public void btnEdit(ActionEvent actionEvent) throws IOException {
        //获取选中行的科研人员信息对象
        person = (Person) tbPerson.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/fxml/EditPerson.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene2);
        //设置窗口图标
//        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/src/main/resources/pictures/exit.png"))));
        //设置窗口标题
        stage.setTitle("修改数据");
        //设置窗体不关闭不能使用其它窗体
        stage.initModality(Modality.APPLICATION_MODAL);
        //设置窗口大小不能改变
        stage.setResizable(false);
        stage.show();
    }

    /**
     * 导出Excel
     *
     * @param actionEvent
     * @throws ParseException
     */
    public void btnExportExcel(ActionEvent actionEvent) throws ParseException {
        //使用当前日期定义要导出的excel文件名
        String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx";
        filename = "D:/MyProject/data/" + filename;//加上要导出的目录
        //调用上这样写在自定义工具类FileTools的方法将stuInfos集合中的数据写入到excel文件
//        System.out.println("Person.class" + Person.class);
        int result = FileTools.writeExcel(filename,Person.class,personlist);
        //设置并显示对话框
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(result + "条数据成功导出至【" + filename + "】文件");
        alert.show();
    }
}