/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.TableService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.CssParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class TableController implements Initializable {

    @FXML
    private TextField txtKeyword;
    @FXML
    private RadioButton rbAll;
    @FXML
    private RadioButton rbEmpty;
    @FXML
    private RadioButton rbFull;
    @FXML
    private TableView<Table> viewTable;
    @FXML
    private TableColumn<Table, String> tableIDCol;
    @FXML
    private TableColumn<Table, String> tableNameCol;
    @FXML
    private TableColumn<Table, Integer> capacityCol;
    @FXML
    private TableColumn<Table, Status> statusCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Định nghĩa cách lấy dữ liệu cho mỗi ô trong TableView
        this.tableIDCol.setCellValueFactory(new PropertyValueFactory<>("tableID"));
        this.tableNameCol.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        this.capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        this.statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTableView();

//        loadTableView("Còn trống");
//        this.txtKeyword.textProperty().addListener((ov, t, t1) -> {
//            try {
//                if (t1.isEmpty()) {
//                    loadTableView();
//                } else {
//                    int capacity = (Integer.getInteger(t1));
//                    loadTableView(capacity);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
    }

    public ObservableList<Table> getTableList() throws SQLException {
        TableService tableService = new TableService();
        List<Table> tables = tableService.getTables();
        ObservableList<Table> list = FXCollections.observableArrayList(tables);
        return list;
    }

    public ObservableList<Table> getTableList(String status) {
      TableService tableService = new TableService();
            List<Table> tables = tableService.getTables(status);
            ObservableList<Table> list = FXCollections.observableArrayList(tables);
            return list;
    }

    public ObservableList<Table> getTableList(int capacity) {
        try {
            TableService tableService = new TableService();
            List<Table> tables = tableService.getTables(capacity);
            ObservableList<Table> list = FXCollections.observableArrayList(tables);
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<Table> getTableList(String status, int capacity) {
        TableService tableService = new TableService();
        List<Table> tables = tableService.getTables(status, capacity);
        ObservableList<Table> list = FXCollections.observableArrayList(tables);
        return list;
    }

    public void rbHandler(ActionEvent event) {
        if (this.rbAll.isSelected()) {
            loadTableView();
            System.out.println(this.rbAll.getText());
        } else if (this.rbEmpty.isSelected()) {
            System.out.println(this.rbEmpty.getText());
            loadTableView("Còn trống");
        } else if (this.rbFull.isSelected()) {
            System.out.println(this.rbFull.getText());
            loadTableView("Đã đặt");
        }
    }

    public void loadTableView() {
        try {
            ObservableList<Table> list = getTableList();
            this.viewTable.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadTableView(String status) {
        ObservableList<Table> list = getTableList(status);
        this.viewTable.setItems(list);
    }

}
