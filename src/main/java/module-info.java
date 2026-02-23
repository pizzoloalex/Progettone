module pizzolo.com.progettone {
    requires javafx.controls;
    requires javafx.fxml;


    opens pizzolo.com.progettone to javafx.fxml;
    exports pizzolo.com.progettone;
    exports pizzolo.com.progettone.controller;
    opens pizzolo.com.progettone.controller to javafx.fxml;
}