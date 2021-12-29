module com.nthn.coffeemanagementapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.nthn.coffeemanagementapp to javafx.fxml;
    exports com.nthn.coffeemanagementapp;
    exports com.nthn.pojo;
    requires org.apache.commons.codec; //Apache Commons Codecs - SHA256
    requires java.base;

}