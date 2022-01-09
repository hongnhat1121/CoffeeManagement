/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nthn.coffeemanagementapp;

import java.net.URL;
import java.util.ResourceBundle;
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
    private ComboBox<?> cbProduct;
    @FXML
    private TableView<?> tbvProduct;
    @FXML
    private TextField tfTable;
    @FXML
    private ComboBox<?> cbCapacity;
    @FXML
    private ComboBox<?> cbStatus;
    @FXML
    private TableView<?> tbvTable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
