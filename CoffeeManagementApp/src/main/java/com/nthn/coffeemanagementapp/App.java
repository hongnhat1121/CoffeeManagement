package com.nthn.coffeemanagementapp;

import com.nthn.configs.JdbcUtils;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Category;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Product;
import com.nthn.pojo.Role;
import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
import com.nthn.services.ProductService;
import com.nthn.services.TableService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
        Scene scene = new Scene(root, 900, 600);

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

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }

    public void loaderController(String resource, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));

        Parent root = loader.load();

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
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
