module com.nthn.coffeemanagementapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.nthn.coffeemanagementapp to javafx.fxml;
    exports com.nthn.coffeemanagementapp;
}
