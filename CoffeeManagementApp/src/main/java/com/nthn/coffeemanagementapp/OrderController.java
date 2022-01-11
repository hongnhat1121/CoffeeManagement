/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Category;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Order;
import com.nthn.pojo.OrderDetail;
import com.nthn.pojo.Product;
import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.EmployeeService;
import com.nthn.services.OrderService;
import com.nthn.services.ProductService;
import com.nthn.services.TableService;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class OrderController implements Initializable {

    @FXML
    private Label lblOrderDate;
    @FXML
    private Label lblTable;
    @FXML
    private Label lblEmployee;

    @FXML
    private ComboBox<Category> cbCategory;
    @FXML
    private TextField txtCapacity;
    @FXML
    private TextField txtName;
    @FXML
    private TableView<OrderDetail> tbvOrder;
    @FXML
    private TableView<Table> tbvTable;
    @FXML
    private TableView<Product> tbvProduct;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnOK;
    @FXML
    private Label lblTotal;
    @FXML
    private TableColumn<OrderDetail, String> productNameCol;
    @FXML
    private TableColumn<OrderDetail, String> quantityCol;
    @FXML
    private TableColumn<OrderDetail, Long> unitPriceCol;

    private Order order;
    private List<OrderDetail> orderDetails;
    private List<Product> listProduct;
    private Table table;
    private Employee employee;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        order = new Order();
        order.setOrderID(Utils.randomID());

        order.setEmployeeID(getEmployee().getEmployeeID());
        
        this.lblEmployee.setText(getEmployee().getFullName());

        orderDetails = new ArrayList<>();
        listProduct = new ArrayList<>();

        this.btnCancel.getStyleClass().setAll("btn", "btn-danger");
        this.btnOK.getStyleClass().setAll("btn", "btn-success");

//        order=new Order(Utils.randomID(), LocalDate.now(),  , employee.getEmployeeID(), tableID, 0);
        this.lblOrderDate.setText("Ngày đặt: " + Utils.converter.toString(LocalDate.now()));

        initTableViewOrder();
        initComboBoxCategory();
        initTableViewTable();
        initTableViewProduct();

        loadTableViewTable(getTableList());
        loadTableViewProduct(getProductList(cbCategory.getValue().getContent()));

        this.txtCapacity.textProperty().addListener((ov, t, t1) -> {
            loadTableViewTable(getTableList(t1));
        });

        this.cbCategory.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
            loadTableViewProduct(getProductList(t1.getContent()));
        });

        this.txtName.textProperty().addListener((ov, t, t1) -> {
            loadTableViewProduct(getProductListByName(this.cbCategory.getValue().getContent(), t1));
        });

        this.tbvProduct.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Product> ov, Product t, Product t1) -> {
            listProduct.add(t1);
            OrderDetail detail = new OrderDetail(order.getOrderID(),
                    t1.getProductID(), t1.getProductName(), 1,
                    t1.getUnitPrice(), null);
            orderDetails.add(detail);
            loadTableViewOrder();
        });

//        this.tbvOrder.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
//            TextInputDialog dialog = new TextInputDialog();
//            dialog.setHeaderText("Nhập số lượng: ");
//            Optional<String> optional = dialog.showAndWait();
//            if (optional.isPresent()) {
//                t1.changeQuantity(Integer.parseInt(optional.get()));
//            }
//            loadTableViewOrder();
//        });
    }

    public void orderHandler(ActionEvent event) {
        order = new Order(order.getOrderID(), LocalDate.now(), BigDecimal.valueOf(calculate()),
                order.getEmployeeID(), table.getTableID(), 0);

        OrderService orderService = new OrderService();
        if (orderService.addOrder(order)) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Order Successfull!", "Menu bạn đặt đã lưu");
        }

    }

    public void cancelHandler(ActionEvent event) {
        this.table = null;
        this.tbvOrder.getItems().removeAll(orderDetails);
        this.orderDetails.clear();
        this.lblTotal.setText("Tổng: ");
    }

    private long calculate() {
        long sum = 0;
        for (int i = 0; i < orderDetails.size(); i++) {
            sum += orderDetails.get(i).getQuantity() * orderDetails.get(i).getUnitPrice();
        }
        return sum;
    }

    private void initComboBoxCategory() {
        ObservableList<Category> list = FXCollections.observableArrayList(Category.DRINK, Category.FOOD);
        this.cbCategory.setItems(list);
        this.cbCategory.setValue(Category.DRINK);
        this.cbCategory.setCursor(Cursor.HAND);
    }

    private void initTableViewTable() {
        TableColumn<Table, String> tableNameCol = new TableColumn<>("Tên bàn");
        TableColumn<Table, Integer> capacityCol = new TableColumn<>("Sức chứa");

        tableNameCol.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        tableNameCol.setSortType(TableColumn.SortType.ASCENDING);

        this.tbvTable.getColumns().addAll(tableNameCol, capacityCol);

        //Chỉ cho phép chọn 1 dòng trên danh sách
        this.tbvTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void initTableViewProduct() {
        TableColumn<Product, String> productNameCol = new TableColumn<>("Tên sản phẩm");
        TableColumn<Product, Long> unitPriceCol = new TableColumn<>("Đơn giá (VNĐ)");

        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        productNameCol.setSortType(TableColumn.SortType.ASCENDING);

        this.tbvProduct.getColumns().addAll(productNameCol, unitPriceCol);

        //Chỉ cho phép chọn 1 dòng trên danh sách
        this.tbvProduct.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void initTableViewOrder() {
        productNameCol = new TableColumn<>("Tên sản phẩm");
        quantityCol = new TableColumn<>("Số lượng");
        unitPriceCol = new TableColumn<>("Đơn giá (VNĐ)");

        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        this.tbvOrder.getColumns().addAll(productNameCol, quantityCol, unitPriceCol);

        //Chỉ cho phép chọn 1 dòng trên danh sách
        this.tbvOrder.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void loadTableViewTable(ObservableList<Table> list) {
        this.tbvTable.setItems(list);

        this.tbvTable.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Table> ov, Table t, Table t1) -> {
                    lblTable.setText("Bàn đặt: " + t1.getTableName());
                    table = t1;
                });
    }

    private void loadTableViewProduct(ObservableList<Product> list) {
        this.tbvProduct.setItems(list);

    }

    private void loadTableViewOrder() {
        ObservableList<OrderDetail> list = FXCollections.observableArrayList(orderDetails);
        this.tbvOrder.setItems(list);
        this.lblTotal.setText("Tổng: " + calculate());
    }

    //Lấy danh sách bàn trống
    private ObservableList<Table> getTableList() {
        try {
            TableService service = new TableService();
            List<Table> tables = service.getTablesByStatus(Status.EMPTY.getContent());
            return FXCollections.observableArrayList(tables);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Lấy danh sách bàn trống theo sức chứa
    private ObservableList<Table> getTableList(String capacity) {
        try {
            TableService service = new TableService();
            List<Table> tables = service.getTablesByAll(capacity, Status.EMPTY.getContent());
            return FXCollections.observableArrayList(tables);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Lấy danh sách sản phẩm theo loại
    private ObservableList<Product> getProductList(String category) {
        ProductService service = new ProductService();
        List<Product> products = service.getProductsByCategory(category);
        return FXCollections.observableArrayList(products);
    }

    //Lấy danh sách sản phẩm theo tên
    private ObservableList<Product> getProductListByName(String category, String name) {
        ProductService service = new ProductService();
        List<Product> products = service.getProducts(category, name);
        return FXCollections.observableArrayList(products);
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

   

}
