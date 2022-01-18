/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.TableService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class TableController {

    public void loadTableViewTable(TableView tbvTable) {
        TableColumn<Table, String> colTableName = new TableColumn<>("Tên bàn");
        colTableName.setCellValueFactory(new PropertyValueFactory("TableName"));
        colTableName.setPrefWidth(200);

        TableColumn<Table, Integer> colCapacity = new TableColumn<>("Sức chứa (người)");
        colCapacity.setCellValueFactory(new PropertyValueFactory("Capacity"));
        colCapacity.setPrefWidth(200);

        TableColumn<Table, String> colStatus = new TableColumn<>("Trạng thái");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colStatus.setPrefWidth(200);

        tbvTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCapacity.setSortType(TableColumn.SortType.ASCENDING);

        tbvTable.getColumns().addAll(colTableName, colCapacity, colStatus);
    }

    public void loadTableDataTable(String kw, TableView tbvTable) throws SQLException {
        TableService ts = new TableService();
        List<Table> tables = ts.getTablesByName(kw);
        tbvTable.setItems(FXCollections.observableList(tables));
    }

    //Lấy danh sách bàn theo bộ lọc: sức chứa, trạng thái
    public void loadTableDateTable1(TableView tbvTable, ComboBox cbCapacity, ComboBox cbStatus) throws SQLException {
        TableService ts = new TableService();
        String capacity = null;
        if (cbCapacity.getSelectionModel().getSelectedItem() != null) {
            capacity = cbCapacity.getSelectionModel().getSelectedItem().toString();
        }

        String status = cbStatus.getSelectionModel().getSelectedItem().toString();
        List<Table> tables = ts.getTablesByAll(capacity, status);
        tbvTable.setItems(FXCollections.observableList(tables));
    }


    //Lọc danh sách sức chứa
    public void loadComboBoxDataCapacity(ComboBox cbCapacity) throws SQLException {
        TableService ts = new TableService();
        List<Table> list = ts.getTables();

        //Lấy list số nguyên sức chứa
        List<Integer> ints = new ArrayList<>();
        list.forEach((t) -> {
            ints.add(t.getCapacity());
        });

        //Đưa list sức chứa về
        Set<Integer> set = new HashSet<>(ints);
        ints.clear();
        set.forEach((t) -> {
            ints.add(t);
        });
        cbCapacity.setItems(FXCollections.observableList(ints));
    }

    public void loadComboBoxDataStatus(ComboBox cbStatus) throws SQLException {
        List<String> s = new ArrayList<>();
        s.add(Status.EMPTY.toString());
        s.add(Status.FULL.toString());

        cbStatus.setItems(FXCollections.observableList(s));
        cbStatus.getSelectionModel().select(0);
    }

}
