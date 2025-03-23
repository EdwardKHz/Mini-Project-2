module com.workshop1.miniproject2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports com.workshop1.miniproject2.models;
    exports com.workshop1.miniproject2.controllers;
    opens com.workshop1.miniproject2.controllers to javafx.fxml;
    opens com.workshop1.miniproject2.views to javafx.fxml;
}