/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.check.LoginChecker;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    @FXML
    private Label lblError;

    private Account account;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnLogin.getStyleClass().setAll("btn", "btn-success");

        this.txtUsername.textProperty().addListener((ov, t, t1) -> {
            if (t1.isEmpty()) {
                this.lblError.setText("Vui lòng nhập username!");
            } else if (t1.contains(" ")) {
                this.lblError.setText("Username chứa khoảng trắng.");
            } else if (t1.matches(".*\\W.*")) {
                this.lblError.setText("Username chứa ký tự đặc biệt.");
            } else if (t1.length() > 20) {
                this.lblError.setText("Username vượt quá 20 ký tự.");
            } else {
                this.lblError.setText("");
            }
        });

        this.txtPassword.textProperty().addListener((ov, t, t1) -> {
            if (t1.isEmpty()) {
                this.lblError.setText("Vui lòng nhập password!");
            } else if (t1.matches(".*\\W.*")) {
                this.lblError.setText("Password chứa ký tự đặc biệt.");
            } else if (t1.length() < 6) {
                this.lblError.setText("Password phải chứa ít nhất 6 ký tự.");
            } else {
                this.lblError.setText("");
            }
        });
    }

    public void loginHandler(ActionEvent event) throws IOException, SQLException {
        if (this.txtUsername.getText().isEmpty() && this.txtPassword.getText().isEmpty()) {
            this.lblError.setText("Vui lòng nhập username và password.");
        } else {
            String username = this.txtUsername.getText();
            String password = this.txtPassword.getText();
            
            LoginChecker checker = new LoginChecker();
            
            if (checker.isSuccessLogin(username, password)) {
                this.account = new AccountService().getAccountByUsername(username);
                
                if (account.getRole() == Role.ADMIN) {
                    loadPrimaryController();
                } else if (account.getRole() == Role.USER) {
                    loadOrderController();
                }
            } else {
                Utils.showAlert(Alert.AlertType.ERROR, "Đăng nhập thất bại!", "Username hoặc password sai.");
            }
        }
    }

    public void registerHandler(ActionEvent event) throws IOException {
        App app = new App();
        app.loaderController("Register.fxml", "Register");
    }

    public void loadPrimaryController() throws IOException {
        App app = new App();
        app.loaderController("Main.fxml", "Coffee Management App");
    }

    public void loadOrderController() throws IOException {
        App app = new App();
        app.loaderController("Order.fxml", "Coffee Management App - Order");
    }
}
