/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Status;
import com.nthn.services.TableService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public void loadTableViewTable(TableView tbvTable) {
        TableColumn colTableName = new TableColumn("Tên bàn");
        colTableName.setCellValueFactory(new PropertyValueFactory("TableName"));
        colTableName.setPrefWidth(200);
        
        TableColumn colCapacity = new TableColumn("Sức chứa");
        colCapacity.setCellValueFactory(new PropertyValueFactory("Capacity"));
        colCapacity.setPrefWidth(200);
        
        TableColumn colStatus = new TableColumn("Trạng thái");
        colCapacity.setCellValueFactory(new PropertyValueFactory("Capacity"));
        colCapacity.setPrefWidth(200);
        
        tbvTable.getColumns().addAll(colTableName, colCapacity);            
    }
    
    public void loadTableDataTable(String kw, TableView tbvTable ) throws SQLException {      
        TableService ts = new TableService();
        tbvTable.setItems(FXCollections.observableList(ts.getTablesByName(kw))); 
    }
    
    public void loadTableDateTable1(TableView tbvTable, ComboBox cbCapacity, ComboBox cbStatus) throws SQLException {
        TableService ts = new TableService();
        
        String capacity = cbCapacity.getSelectionModel().getSelectedItem().toString();      
        String status = cbStatus.getSelectionModel().getSelectedItem().toString();
        
        tbvTable.setItems(FXCollections.observableList(ts.getTablesByAll(capacity, status)));  
    }
    
    public void loadTableDateTable2(TableView tbvTable, ComboBox cbCapacity, ComboBox cbStatus) throws SQLException {
        TableService ts = new TableService();
        
        String capacity = cbCapacity.getSelectionModel().getSelectedItem().toString();      
        String status = cbStatus.getSelectionModel().getSelectedItem().toString();
        
        tbvTable.setItems(FXCollections.observableList(ts.getTablesByAll(capacity, status)));
    }
    
    public void loadComboBoxDataCapacity(ComboBox cbCapacity) throws SQLException {
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        cbCapacity.setItems(FXCollections.observableList(s)); 
    }
    
     public void loadComboBoxDataStatus(ComboBox cbStatus) throws SQLException {
        List<String> s = new ArrayList<>();
        s.add(Status.EMPTY.toString());
        s.add(Status.FULL.toString());
        cbStatus.setItems(FXCollections.observableList(s)); 
        cbStatus.getSelectionModel().select(0);
    }

}
