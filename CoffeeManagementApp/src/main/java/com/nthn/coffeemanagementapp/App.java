package com.nthn.coffeemanagementapp;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Account;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * JavaFX App
 */
public class App extends Application {

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
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
        stage.setTitle("Coffee Management App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
       launch();
//        Account account=new AccountService().getAccountByID("2c577442-b501-406d-a48b-6fac14941b4c");
//        System.out.println(account.getUsername());
//        EmployeeService service=new EmployeeService();
//        System.out.println(service.getEmployees().get(1).getFullName());
//new EmployeeService().deleteEmployee("cc77cadd-96ce-4a61-beee-831474b4cd84", "2c577442-b501-406d-a48b-6fac14941b4c");
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

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

}
