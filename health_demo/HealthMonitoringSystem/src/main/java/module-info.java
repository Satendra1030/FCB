module com.example.healthmonitoringsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.healthmonitoringsystem to javafx.fxml;
    exports com.example.healthmonitoringsystem;
}