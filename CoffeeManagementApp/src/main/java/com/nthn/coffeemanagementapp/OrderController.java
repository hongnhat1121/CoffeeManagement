/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.Category;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Order;
import com.nthn.pojo.OrderDetail;
import com.nthn.pojo.Product;
import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.OrderDetailService;
import com.nthn.services.OrderService;
import com.nthn.services.ProductService;
import com.nthn.services.TableService;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
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
        init();
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
        this.tbvTable.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Table> ov, Table t, Table t1) -> {
                    lblTable.setText("Bàn đặt: " + t1.getTableName());
                    table = t1;
                });
        this.txtName.textProperty().addListener((ov, t, t1) -> {
            loadTableViewProduct(getProductListByName(this.cbCategory.getValue().getContent(), t1));
        });

        this.tbvProduct.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
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
        if (this.table == null && this.tbvOrder.getItems().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Order Error!", "Chưa có thông tin bàn và món!");
        } else if (this.tbvOrder.getItems().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Order Error!", "Chưa chọn món!");
        } else if (this.table == null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Order Error!", "Chưa chọn bàn");
        } else {
            order = new Order(order.getOrderID(), LocalDate.now(), BigDecimal.valueOf(calculate()),
                    order.getEmployeeID(), table.getTableID(), 0);

            OrderService orderService = new OrderService();
            if (orderService.addOrder(order)) {
                orderDetails.forEach((t) -> {
                    OrderDetailService detailService = new OrderDetailService();
                    detailService.addOrderDetail(t);
                });

                Utils.showAlert(Alert.AlertType.INFORMATION, "Order Successfull!", "Menu bạn đặt đã lưu");
                
            }
            init();
        }
    }

    public void cancelHandler(ActionEvent event) {
        init();
    }

    private void init() {
        this.orderDetails = new ArrayList<>();
        this.listProduct = new ArrayList<>();
        this.table = null;
        this.order = new Order();
        this.order.setOrderID(Utils.randomID());
        this.order.setEmployeeID("54cf6d95-fdff-4477-8237-805d07e90217");

        this.btnCancel.getStyleClass().setAll("btn", "btn-danger");
        this.btnOK.getStyleClass().setAll("btn", "btn-success");
        this.lblOrderDate.setText("Ngày đặt: " + Utils.converter.toString(LocalDate.now()));
        this.tbvOrder.getItems().removeAll(orderDetails);
        this.lblTotal.setText("Tổng:...");
        this.lblTable.setText("Bàn đặt: ...");
        this.lblEmployee.setText("Nhân viên:...");
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
//
//        ObservableList<Product> list = getProductList(category);
//        ObservableList<Product> result = null;
//        list.forEach((t) -> {
//            if (t.getProductName().contains(name)) {
//                result.add(t);
//            }
//        });
//        return FXCollections.observableArrayList(result);
    }

}
