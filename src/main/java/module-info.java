module com.example.demo {
    exports munch;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    opens munch to javafx.fxml;
    exports com.example.demo;
}