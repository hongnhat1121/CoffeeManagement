/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.Order;
import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.OrderService;
import com.nthn.services.TableService;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class PaymentController implements Initializable {

    @FXML
    private ComboBox<Table> cbTableFull;
    @FXML
    private ComboBox<LocalDate> cbLocalDate;
    @FXML
    private TableView<Order> tbvOrder;
    @FXML
    private Button btnPayment;
    @FXML
    private TextField txtTableName;

    private List<Table> tables;
    private List<Order> orders;

    private final TableService ts = new TableService();
    private final OrderService os = new OrderService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initComboBoxTable();
            loadComboBoxTable(getTableListIsFull());
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        initTableView();
        loadTableViewOrderNotPay(getOrderListNotPay());

        this.cbTableFull.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
            loadTableViewOrderNotPay(getOrderListNotPay(t1.getTableID()));
        });

        this.txtTableName.textProperty().addListener((observable, oldValue, newValue) ->
        {loadTableViewOrderNotPay(getOrderListNotPay(newValue));}
        );
    }

    public void btnPaymentHandler(ActionEvent event) {
        try {
            Order order = this.tbvOrder.getSelectionModel().getSelectedItem();
            os.updateOrder(order);
            Table table;
            table = ts.getTable(order.getTableID());
            table.setStatus(Status.EMPTY);

            ts.updateTable(table);

            Utils.showAlert(Alert.AlertType.INFORMATION, "Payment Success", "Hóa đơn của bàn " + table.getTableName() + " đã được thanh toán.");

            loadComboBoxTable(getTableListIsFull());
            loadTableViewOrderNotPay(getOrderListNotPay());
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Khởi tạo combobox các bàn đã đặt
    private void initComboBoxTable() throws SQLException {
        ObservableList<Table> list = FXCollections.observableArrayList(getTableListIsFull());
        this.cbTableFull.setPromptText("Chọn bàn đã đặt");
    }

    //Lấy danh sách các bàn đã đặt
    private ObservableList<Table> getTableListIsFull() throws SQLException {
        tables = ts.getTablesByStatus(Status.FULL.getContent());
        return FXCollections.observableArrayList(tables);
    }

    //Đưa danh sách bàn đã đặt lên combobox
    private void loadComboBoxTable(ObservableList<Table> list) {
        this.cbTableFull.setItems(list);
        this.cbTableFull.setPromptText("Chọn bàn đã đặt");
    }

    private void initTableView() {
        TableColumn<Order, String> orderDateCol = new TableColumn<>("Ngày đặt");
        TableColumn<Order, Table> tableCol = new TableColumn<>("Tên bàn");
        TableColumn<Order, Long> totalCol = new TableColumn<>("Tổng tiền (VNĐ)");

        orderDateCol.setCellValueFactory((param) -> {
            return new SimpleObjectProperty<>(Utils.converter.toString(param.getValue().getOrderDate()));
        });

        tableCol.setCellValueFactory((TableColumn.CellDataFeatures<Order, Table> param) -> {
            try {
                return new SimpleObjectProperty<>(ts.getTable(param.getValue().getTableID()));
            } catch (SQLException ex) {
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        });
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        orderDateCol.setSortType(TableColumn.SortType.DESCENDING);

        this.tbvOrder.getColumns().addAll(orderDateCol, tableCol, totalCol);

        this.tbvOrder.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void loadTableViewOrderNotPay(ObservableList<Order> list) {
        this.tbvOrder.setItems(list);
    }

    //Lấy danh sách tất cả các hóa đơn chưa thanh toán
    private ObservableList<Order> getOrderListNotPay() {
        orders = os.getOrdersNotPay();
        return FXCollections.observableArrayList(orders);
    }

    private ObservableList<Order> getOrderListNotPay(String tableID) {
        orders = os.getOrdersNotPay(tableID);
        return FXCollections.observableArrayList(orders);
    }

}
