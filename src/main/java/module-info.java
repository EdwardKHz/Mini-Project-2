module com.workshop1.miniproject2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.workshop1.miniproject2 to javafx.graphics, javafx.fxml;
    opens com.workshop1.miniproject2.controllers to javafx.fxml;
    opens com.workshop1.miniproject2.models to javafx.base;

    exports com.workshop1.miniproject2;
}
