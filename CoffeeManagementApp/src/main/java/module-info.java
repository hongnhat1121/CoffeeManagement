module com.nthn.coffeemanagementapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.nthn.coffeemanagementapp to javafx.fxml;
    exports com.nthn.coffeemanagementapp;
}
