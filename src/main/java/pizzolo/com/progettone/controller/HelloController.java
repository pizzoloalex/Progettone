package pizzolo.com.progettone.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.*;
import java.security.Key;
import java.util.jar.JarOutputStream;

/**
 * la mappa si muove e si aggiorna ad ogni movimento della macchina
 * la macchina sta ferma e fa gli effetti per far sembrare che si muove
 */

public class HelloController {

    @FXML
    private ImageView mappa;//contiene la mappa
    @FXML
    //TODO eventualmente si puo aggiungere alla ImageView sopra collegandoli con una collisione
    private Canvas ostacoli;//serve per disegnare gli ostacoli
    @FXML
    private ImageView macchina;//contiene la macchina
    @FXML
    private Pane schermo;//schermo di tutto il gioco


    public void initialize() {
        schermo.setFocusTraversable(true);
        schermo.requestFocus();
        schermo.setOnKeyPressed(this::muoviMacchina);
        Image img = new Image(getClass().getResource("/pizzolo/com/progettone/images/macchina.png").toExternalForm());
        macchina.setImage(img);
        macchina.setFitWidth(200);
        macchina.setFitHeight(200);
    }

    /**
     * muuove l'immagine della macchina di tot step con i tasti
     * @param event
     */
    public void muoviMacchina(KeyEvent event) {
        double step = 10;
        if (event.getCode() == KeyCode.W) {
            macchina.setLayoutY(macchina.getLayoutY() - step);
        }
            if (event.getCode() == KeyCode.S) {
                macchina.setLayoutY(macchina.getLayoutY() + step);
            }
            if (event.getCode() == KeyCode.A) {
                macchina.setLayoutX(macchina.getLayoutX() - step);
            }
            if (event.getCode() == KeyCode.D) {
                macchina.setLayoutX(macchina.getLayoutX() + step);
            }


    }
}
