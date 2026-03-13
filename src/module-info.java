module proiect_pibd_javaFX {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens app to javafx.graphics;
    opens app.controller to javafx.fxml;
    opens app.model to javafx.base;
}