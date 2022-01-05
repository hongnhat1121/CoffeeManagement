/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.JdbcUtils;
import com.nthn.configs.Utils;
import com.nthn.services.AccountService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (this.txtUsername.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter username!");
            return;
        }
        if (this.txtPassword.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter password!");
            return;
        }
        this.checkLogin(this.txtUsername.getText(), this.txtPassword.getText());
    }

    public void registerHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));

        Parent root = loader.load();

        RegisterController controller = loader.getController();

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        Stage stage = new Stage();
        stage.setOnHiding((t) -> {
            try {
                Connection c = JdbcUtils.getConnection();
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    public void loadPrimaryController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));

        Parent root = loader.load();

        PrimaryController controller = loader.getController();

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        Stage stage = new Stage();
        stage.setOnHiding((t) -> {
            try {
                Connection c = JdbcUtils.getConnection();
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        stage.setTitle("Coffee Management App");
        stage.setScene(scene);
        stage.show();
    }

    private void checkLogin(String username, String password) throws SQLException, IOException {
        AccountService service = new AccountService();
        boolean result = service.checkAccount(username, password);

        if (result) {
            loadPrimaryController();
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Login error", "Please enter correct username and password");
        }
    }
}
