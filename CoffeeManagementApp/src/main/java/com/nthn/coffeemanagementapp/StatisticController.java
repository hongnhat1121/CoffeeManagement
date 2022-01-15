/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.Order;
import com.nthn.pojo.Table;
import com.nthn.services.*;

import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author HONGNHAT
 */
public class StatisticController {
    private final TableService ts = new TableService();
    private final OrderService os = new OrderService();

    public void loadTableViewOrder(TableView<Order> tableView) {
        TableColumn<Order, String> orderDateCol = new TableColumn<>("Ngày đặt");
        TableColumn<Order, Table> tableCol = new TableColumn<>("Tên bàn");
        TableColumn<Order, Long> totalCol = new TableColumn<>("Tổng tiền (VNĐ)");
        TableColumn<Order, String> paymentCol = new TableColumn<>("Ghi chú");

        orderDateCol.setCellValueFactory((param) -> {
            return new SimpleObjectProperty<>(Utils.converter.toString(param.getValue().getOrderDate()));
        });
        tableCol.setCellValueFactory((TableColumn.CellDataFeatures<Order, Table> param) -> {
            return new SimpleObjectProperty<>(ts.getTable(param.getValue().getTableID()));
        });
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        paymentCol.setCellValueFactory(param -> {
            if (param.getValue().getPayment() == 0)
                return new SimpleObjectProperty<>("Chưa thanh toán");
            else return new SimpleObjectProperty<>("Đã thanh toán");
        });

        orderDateCol.setSortType(TableColumn.SortType.DESCENDING);
        tableView.getColumns().addAll(orderDateCol, tableCol, totalCol, paymentCol);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void loadTableDataOrder(TableView<Order> tableView) {
        tableView.setItems(FXCollections.observableList(os.getOrders()));
    }

    public void loadTableDataOrder(TableView<Order> tableView, String tableName) {
        tableView.setItems(FXCollections.observableList(os.getOrders(tableName)));
    }

    public void loadTableDataOrder(TableView<Order> tableView, LocalDate localDate) {
        tableView.setItems(FXCollections.observableList(os.getOrders(localDate)));
    }


}
