/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.JdbcUtils;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Role;
import com.nthn.services.AccountService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * FXML Controller class
 *
 * @author HONGNHAT
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txtFullname;
    @FXML
    private RadioButton rbMale;
    @FXML
    private RadioButton rbFemale;
    @FXML
    private RadioButton rbOther;
    @FXML
    private DatePicker dpBirthDate;
    @FXML
    private TextField txtIdentityCard;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAddress;
    @FXML
    private DatePicker dpHireDate;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirm;

    @FXML
    private Button btnRegister;
    @FXML
    private Button btnSwitchLogin;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnRegister.getStyleClass().setAll("btn", "btn-success");

        this.dpBirthDate.setConverter(Utils.converter);
        this.dpBirthDate.setValue(LocalDate.now());

        this.dpHireDate.setConverter(Utils.converter);
        this.dpHireDate.setValue(LocalDate.now());
    }

    public void registerHandler(ActionEvent event) {
        if (this.txtFullname.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter fullname!");
            this.txtFullname.setFocusTraversable(true);
            return;
        }
        if (this.txtIdentityCard.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter identity card!");
            this.txtIdentityCard.setFocusTraversable(true);
            return;
        }
        if (this.txtPhone.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter phone number!");
            this.txtPhone.setFocusTraversable(true);
            return;
        }

        if (this.txtUsername.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Login Error!", "Please enter username!");
            this.txtUsername.setFocusTraversable(true);
            return;
        }
        if (this.txtPassword.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter password");
            this.txtPassword.setFocusTraversable(true);
            return;
        }
        if (this.txtConfirm.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please enter confirm password");
            this.txtConfirm.setFocusTraversable(true);
            return;
        }
        if (!this.txtConfirm.getText().equals(this.txtPassword.getText())) {
            Utils.showAlert(Alert.AlertType.ERROR, "Register Error!", "Please re-enter confirm password");
            this.txtConfirm.setFocusTraversable(true);
            return;
        }
        Utils.showAlert(Alert.AlertType.INFORMATION, "Registration successfull!", "Welcome to Coffee Management App");
        String fullname = this.txtFullname.getText();
        Gender gender;
        if (rbFemale.isSelected()) {
            gender = Gender.FEMALE;
        } else if (rbMale.isSelected()) {
            gender = Gender.MALE;
        } else if (rbOther.isSelected()) {
            gender = Gender.OTHER;
        }
        LocalDate birthDate = this.getLocalDate(this.dpBirthDate);
        String identityCard = this.txtIdentityCard.getText();
        String phone = this.txtPhone.getText();
        String address = this.txtAddress.getText();
        LocalDate hireDate = this.getLocalDate(this.dpHireDate);
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        String confirm = this.txtConfirm.getText();

        Account account = new Account(Utils.randomID(), username, password, Active.AVAILABLE, Role.USER);
        new AccountService().addAccount(account);
    }

    public void loginHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        Parent root = loader.load();

        LoginController controller = loader.getController();

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
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    private LocalDate getLocalDate(DatePicker datePicker) {
        TextField textField = datePicker.getEditor();
        String date = textField.getText();
        LocalDate result = Utils.converter.fromString(date);
        return result;
    }
}
