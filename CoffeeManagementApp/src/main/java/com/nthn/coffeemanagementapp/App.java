package com.nthn.coffeemanagementapp;

import com.nthn.pojo.Active;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Role;
import com.nthn.pojo.State;
import com.nthn.pojo.Status;
import com.nthn.services.ActiveService;
import com.nthn.services.GenderService;
import com.nthn.services.RoleService;
import com.nthn.services.StateService;
import com.nthn.services.StatusService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

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

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        //launch();
//        Account account = new Account();
//        account.inputUsername();
//        account.inputPassword();
//        account.setActive(Active.AVAILABLE);
//        account.setRole(Role.USER);
////        account.display();

//        RoleService roleService = new RoleService();
//        roleService.addRole(Role.USER);
//        roleService.addRole(Role.ADMIN);
//        List<String> results = roleService.getRoles();
//        for (String result : results) {
//            System.out.println("com.nthn.coffeemanagementapp.App.main(): " + result.toString());
//        }


//        ActiveService activeService = new ActiveService();
//        activeService.addActive(Active.AVAILABLE);
//        activeService.addActive(Active.LOCK);

//            StateService stateService = new StateService();
//            stateService.addState(State.SERVE);
//            stateService.addState(State.BARTENDER);
//            stateService.addState(State.RECEPTION);
            
//            StatusService statusService= new StatusService();
//            statusService.addStatus(Status.EMPTY);
//            statusService.addStatus(Status.FULL);
            
//            GenderService genderService = new GenderService();
//            genderService.addGender(Gender.OTHER);
//            genderService.addGender(Gender.FEMALE);
//            genderService.addGender(Gender.MALE);
                    
                    

    }

}
