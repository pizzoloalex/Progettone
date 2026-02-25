package pizzolo.com.progettone.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pizzolo.com.progettone.model.ImmaginiStrada;

import java.net.URL;
import java.util.ArrayList;

/**
 * la mappa si muove e si aggiorna ad ogni movimento della macchina
 * la macchina sta ferma e fa gli effetti per far sembrare che si muove
 */

public class HelloController {
    @FXML
    private VBox vBox;
    @FXML
    private Pane schermo;//mostra tutto il gioco
    @FXML
    private VBox mappa;//mostra la mappa
    @FXML
    private ImageView macchina;//mostra la macchina

    public void initialize(){
        Image strada = new Image(getClass().getResource("/pizzolo/com/progettone/images/continuo_strada.png").toExternalForm());

        for (int i = 0; i < 3; i++){
            ImageView view = new ImageView(strada);
            view.setFitWidth(100);
            view.setFitHeight(100);

            mappa.getChildren().add(view);
        }
    }

}
