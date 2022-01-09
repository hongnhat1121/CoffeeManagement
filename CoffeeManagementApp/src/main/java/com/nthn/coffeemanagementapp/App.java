package com.nthn.coffeemanagementapp;

import com.nthn.configs.JdbcUtils;
import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Role;
import com.nthn.pojo.Status;
import com.nthn.pojo.Table;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
import com.nthn.services.TableService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
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

//        Account a=new Account(Utils.randomID(), "user1", "User2022", Active.AVAILABLE, Role.USER);
//        new AccountService().addAccount(a);
//        EmployeeService service = new EmployeeService();
//        Employee employee = new Employee("23c74463-75ea-4835-b0b7-9e6545bac000",
//                LocalDate.now(), "Nguyễn Thị Hồng Nhật", Utils.converter.fromString("10/10/2001"),
//                Gender.FEMALE, "342010930", "Đồng Tháp", "0836479646",
//                "2c577442-b501-406d-a48b-6fac14941b4c");
//        service.addEmployee(employee);
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
