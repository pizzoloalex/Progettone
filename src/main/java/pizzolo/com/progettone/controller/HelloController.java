package pizzolo.com.progettone.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HelloController {
    public Pane pane;
    @FXML
    private Canvas canvas;
    @FXML
    public void inizia() {
        Circle cr = new Circle(150,150,20, Color.GREENYELLOW);
        pane.getChildren().add(cr);//aggiunge al figlio, quindi il canvas, il cerchio
        TranslateTransition fd = new TranslateTransition(Duration.seconds(5), cr);//anima il cerchio
        //partenza
        fd.setFromX(200);
        fd.setFromY(200);
        //arrivo
        fd.setToX(88);
        fd.setToY(120);
        fd.setInterpolator(Interpolator.LINEAR);
        FadeTransition op = new FadeTransition(Duration.seconds(3), cr);
        op.setFromValue(1.0);
        op.setToValue(0.0);
        op.setCycleCount(1);
        op.play();
        fd.play();
    }
}