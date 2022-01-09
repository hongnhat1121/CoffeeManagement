/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Product;
import com.nthn.services.ProductService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ProductController {
    
    public void loadTableViewProduct(TableView<Product> tbvProduct) {
        TableColumn colProductName = new TableColumn("Product name");
        colProductName.setCellValueFactory(new PropertyValueFactory("ProductName"));
        colProductName.setPrefWidth(300);
        
        TableColumn colUnitPrice = new TableColumn("Unit price");
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("UnitPrice"));
        colUnitPrice.setPrefWidth(200);
        
        tbvProduct.getColumns().addAll(colProductName, colUnitPrice);            
    }
    
    public void loadTableDataProduct(String kw, TableView<Product> tbvProduct, ComboBox cbProduct) throws SQLException {      
        ProductService ps = new ProductService();
        if(cbProduct.getSelectionModel().getSelectedItem().toString().equals("ProductName"))
            tbvProduct.setItems(FXCollections.observableList(ps.getProductsByName(kw)));    
        else
            tbvProduct.setItems(FXCollections.observableList(ps.getProductsByUnitPrice(kw)));    
    }
    
    public void loadComboBoxDataProduct(ComboBox cbProduct) throws SQLException {
        ProductService ps = new ProductService();
        cbProduct.setItems(FXCollections.observableList(ps.getColumnsName())); 
        cbProduct.getSelectionModel().select(0);
    }
}
