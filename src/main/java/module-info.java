module pizzolo.com.progettone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.xml.dom;


    opens pizzolo.com.progettone to javafx.fxml;
    opens pizzolo.com.progettone.controller to javafx.fxml;
    exports pizzolo.com.progettone;
}