/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Product;
import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class MainController implements Initializable {

    @FXML
    private TextField tfProduct;
    @FXML
    private ComboBox cbProduct;
    @FXML
    private TableView<Product> tbvProduct;
    @FXML
    private TextField tfTable;
    @FXML
    private ComboBox cbCapacity;
    @FXML
    private ComboBox cbStatus;
    @FXML
    private TableView<Table> tbvTable;
    
    
    private final ProductController pc = new ProductController();
    
    private final TableController tc = new TableController();
    @FXML
    private Button btnReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.pc.loadTableViewProduct(tbvProduct);
        this.tc.loadTableViewTable(tbvTable);
        System.out.print(Status.getByContent("Còn trống").name());
            
        try {
            this.pc.loadComboBoxDataProduct(cbProduct);
            this.tc.loadComboBoxDataCapacity(cbCapacity);
            this.tc.loadComboBoxDataStatus(cbStatus);
            this.pc.loadTableDataProduct(null, tbvProduct, cbProduct);
            this.tc.loadTableDataTable(null, tbvTable);
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        this.tfProduct.textProperty().addListener((evt) -> {
            try {
                this.pc.loadTableDataProduct(this.tfProduct.getText(), tbvProduct, cbProduct);
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
         this.tfTable.textProperty().addListener((evt) -> {
            try {
                this.tc.loadTableDataTable(this.tfTable.getText(), tbvTable);
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
        this.cbCapacity.getSelectionModel().selectedItemProperty().addListener((evt) -> {
            try {
                this.tc.loadTableDateTable1(tbvTable, cbCapacity, cbStatus);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.cbStatus.getSelectionModel().selectedItemProperty().addListener((evt) -> {
            try {
                this.tc.loadTableDateTable1(tbvTable, cbCapacity, cbStatus);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @FXML
    private void ResetComboBox(ActionEvent event) throws SQLException {
        this.cbCapacity.valueProperty().set(null);
        this.cbStatus.valueProperty().set(null);
        this.tc.loadComboBoxDataCapacity(cbCapacity);
        this.tc.loadComboBoxDataStatus(cbStatus);
        this.tc.loadTableDataTable(null, tbvTable);
    }
    
}
