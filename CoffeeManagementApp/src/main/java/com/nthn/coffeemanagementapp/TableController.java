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
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class TableController {

    public List<Table> tables;

    public void loadTableViewTable(TableView tbvTable) {
        TableColumn<Table, String> colTableName = new TableColumn<>("Tên bàn");
        colTableName.setCellValueFactory(new PropertyValueFactory("TableName"));
        colTableName.setPrefWidth(200);

        TableColumn<Table, Integer> colCapacity = new TableColumn<>("Sức chứa");
        colCapacity.setCellValueFactory(new PropertyValueFactory("Capacity"));
        colCapacity.setPrefWidth(200);
//
//        TableColumn<Table, Status> colStatus = new TableColumn<>("Trạng thái");
////        colStatus.setCellValueFactory(param -> {
////            return new SimpleObjectProperty<>(param.getValue().getStatus());
////        });
//        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
//        colStatus.setPrefWidth(200);

        tbvTable.getColumns().addAll(colTableName, colCapacity);
    }

    public void loadTableDataTable(String kw, TableView tbvTable) throws SQLException {
        TableService ts = new TableService();
        this.tables = ts.getTablesByName(kw);
        tbvTable.setItems(FXCollections.observableList(tables));
    }

    public void loadTableDateTable1(TableView tbvTable, ComboBox cbCapacity, ComboBox cbStatus) throws SQLException {
        TableService ts = new TableService();
        String capacity = null;
        if (cbCapacity.getSelectionModel().getSelectedItem() != null) {
            capacity = cbCapacity.getSelectionModel().getSelectedItem().toString();
        }

        String status = cbStatus.getSelectionModel().getSelectedItem().toString();
        this.tables = ts.getTablesByAll(capacity, status);
        tbvTable.setItems(FXCollections.observableList(tables));
    }

    public void loadComboBoxDataCapacity(ComboBox cbCapacity) throws SQLException {
        List<String> s = new ArrayList<>();

        TableService ts = new TableService();
        List<Table> list = ts.getTables();
        
        List<Integer> integers=new ArrayList<>();
        list.forEach((t) -> {
            integers.add(t.getCapacity());
        });
        
        Set<Integer> set=new HashSet<>(integers);
        integers.clear();
        set.forEach((t) -> {
            integers.add(t);
        });
        cbCapacity.setItems(FXCollections.observableList(integers));
    }

    public void loadComboBoxDataStatus(ComboBox cbStatus) throws SQLException {
        List<String> s = new ArrayList<>();
        s.add(Status.EMPTY.toString());
        s.add(Status.FULL.toString());
        cbStatus.setItems(FXCollections.observableList(s));
        cbStatus.getSelectionModel().select(0);
    }

}
