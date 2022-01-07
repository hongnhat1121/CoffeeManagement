/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.JdbcUtils;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.services.AccountService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSwitchRegister;

    private Account account;
    private final AccountService accountService = new AccountService();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnLogin.getStyleClass().setAll("btn", "btn-success");
    }

    public void loginHandler(ActionEvent event) throws SQLException, IOException {
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        if (this.txtUsername.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter username!");
            return;
        }
        if (this.txtPassword.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter password!");
            return;
        }

        account = accountService.getAccountByUsername(username);
        if (account != null) {
            if (account.getPassword().equals(DigestUtils.sha256Hex(password))) {
                loadPrimaryController();
            } else {
                Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter correct password!");
                return;
            }
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Username doesn't exist!");
            return;
        }

        if (username.contains(" ") || username.matches(".*\\W.*") || username.length() > 20) {
            
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Login Failed!", "Please enter correct password!");
        }
    }
}

public void registerHandler(ActionEvent event) throws IOException {
        App app = new App();
        app.loaderController("Register.fxml", "Register");
    }

    public void loadPrimaryController() throws IOException {
        App app = new App();
        app.loaderController("primary.fxml", "Coffee Management App");
    }
}
