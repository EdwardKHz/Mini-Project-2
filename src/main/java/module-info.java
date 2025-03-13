module com.workshop1.miniproject2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.workshop1.miniproject2 to javafx.fxml;
    exports com.workshop1.miniproject2;
}