/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Account;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import com.nthn.services.RoleService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class FXML_LoginController implements Initializable {

    @FXML
    private ComboBox<Role> cbRoles;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnReset;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbRoles.getItems().addAll(new RoleService().getRoles());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FXML_LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loginHandler(ActionEvent actionEvent) throws SQLException {
        Account a = new Account(UUID.randomUUID().toString(), this.txtUsername.getText(), this.txtPassword.getText(), this.cbRoles.getSelectionModel().getSelectedItem().getRoleID());
        a.display();
        new AccountService().addAccount(a);
        
    }

    public void resetHandler(ActionEvent actionEvent) {

    }

}
