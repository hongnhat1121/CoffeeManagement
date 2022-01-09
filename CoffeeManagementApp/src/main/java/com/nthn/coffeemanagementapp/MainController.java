/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Product;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
//    @FXML
//    private TextField tfTable;
//    @FXML
//    private ComboBox<?> cbCapacity;
//    @FXML
//    private ComboBox<?> cbStatus;
//    @FXML
//    private TableView<?> tbvTable;
    
    
    private final ProductController pc = new ProductController();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.pc.loadTableViewProduct(tbvProduct);
            
        try {
            this.pc.loadComboBoxDataProduct(cbProduct);
            this.pc.loadTableDataProduct(null, tbvProduct, cbProduct);
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
    }    
    
}
