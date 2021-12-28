package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Role;
import com.nthn.services.RoleService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //launch();
//        Account account = new Account();
//        account.inputUsername();
//        account.inputPassword();
//        account.setActive(Active.AVAILABLE);
//        account.setRole(Role.USER);
////        account.display();
        RoleService roleService = new RoleService();
//        roleService.addRole(Role.USER);
//        roleService.addRole(Role.ADMIN);
        roleService.deleteRole(Role.USER);

    }

}
