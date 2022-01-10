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
    public void loadTableViewProduct(TableView tbvProduct) {
        TableColumn colProductName = new TableColumn("Tên sản phẩm");
        colProductName.setCellValueFactory(new PropertyValueFactory("ProductName"));
        colProductName.setPrefWidth(200);
        
        TableColumn colUnitPrice = new TableColumn("Giá tiền");
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("UnitPrice"));
        colUnitPrice.setPrefWidth(200);
        
        TableColumn colCategory = new TableColumn("Thể loại");
        colCategory.setCellValueFactory(new PropertyValueFactory("Category"));
        colCategory.setPrefWidth(200);
        
        tbvProduct.getColumns().addAll(colProductName, colUnitPrice, colCategory);            
    }
    
    public void loadTableDataProduct(String kw, TableView tbvProduct, ComboBox cbProduct) throws SQLException {      
        ProductService ps = new ProductService();
        if(cbProduct.getSelectionModel().getSelectedIndex() == 0)
            tbvProduct.setItems(FXCollections.observableList(ps.getProductsByName(kw)));    
        else
            tbvProduct.setItems(FXCollections.observableList(ps.getProductsByUnitPrice(kw)));         
    }
    
    public void loadComboBoxDataProduct(ComboBox cbProduct) throws SQLException {
        List<String> s = new ArrayList<>();
        s.add("Tên sản phẩm");
        s.add("Giá tiền");
        cbProduct.setItems(FXCollections.observableList(s)); 
        cbProduct.getSelectionModel().select(0);
    }
}
