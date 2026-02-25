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
import javafx.util.Duration;
import pizzolo.com.progettone.model.ImmaginiStrada;

import java.util.ArrayList;

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

        // Immagine strada
        Image strada = new Image(getClass().getResource("/pizzolo/com/progettone/images/continuo_strada.png").toExternalForm());
        mappa.setFitHeight(500);
        mappa.setFitWidth(500);
        mappa.setPreserveRatio(true);
        mappa.setImage(strada);

        //continuo strada
        Image continuoStrada = new Image(
                getClass().getResource("/pizzolo/com/progettone/images/continuo_strada.png").toExternalForm()
        );

        double tileHeight = 500; // l’altezza che vuoi visualizzare

        for (int i = 0; i < 4; i++) {
            ImageView tile = new ImageView(continuoStrada);

            tile.setFitWidth(500);
            tile.setFitHeight(tileHeight);

            tile.setLayoutX(0);
            tile.setLayoutY(i * tileHeight);

            schermo.getChildren().add(tile);
        }




        // Immagine macchina
        Image img = new Image(getClass().getResource("/pizzolo/com/progettone/images/macchina.png").toExternalForm());
        macchina.setImage(img);
        macchina.setFitWidth(100);
        macchina.setFitHeight(100);

        // Posiziona la macchina sopra la strada
        macchina.setLayoutX(mappa.getLayoutX() + mappa.getFitWidth() / 2 - macchina.getFitWidth() / 2);
        macchina.setLayoutY(mappa.getLayoutY() + mappa.getFitHeight() / 2 - macchina.getFitHeight() / 2);
    }


    /**
     * muuove l'immagine della macchina di tot step con i tasti
     *
     * @param event
     */
    public void muoviMacchina(KeyEvent event) {
        double step = 10;
        TranslateTransition tr = new TranslateTransition(Duration.millis(10), schermo);

        if (event.getCode() == KeyCode.W) {
            tr.setByY(step);
        }
        if (event.getCode() == KeyCode.S) {
            tr.setByY(-step);
        }
        if (event.getCode() == KeyCode.A) {
            //TODO calcolare le curve
            tr.setByX(-step);
        }
        if (event.getCode() == KeyCode.D) {
            //TODO calcolare le curve
            tr.setByX(step);
        }
        tr.play();


    }
}
