package pizzolo.com.progettone.model;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * gestisce i movimenti della macchina
 */

public class Movimento extends AnimationTimer {

    private double speed = 10;
    private final Pane mappa;
    private Mappa[] immagini;
    private Timeline accelerazione; //accelerazione per la macchina

    public Movimento(Pane mappa, Mappa[] immagini) {
        this.mappa = mappa;
        this.immagini = immagini;
    }

    /**
     * gestisce i movimenti della macchina ad ogni frame
     *
     * @param l
     */
    @Override
    public void handle(long l) {
        // Movimento verticale di ogni immagine
        for (Mappa img : immagini) {
            img.getView().setLayoutY(img.getView().getLayoutY() + speed);
            // Loop infinito: quando un'immagine esce, torna sopra
            if (img.getView().getLayoutY() >= mappa.getHeight()) {
                img.getView().setLayoutY(img.getView().getLayoutY() - img.getView().getFitHeight() * immagini.length);
            }
        }
    }

    /**
     * ogni 15 secondi la velocita aumenta
     */
    public void accelera() {
        accelerazione = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> this.speed += 10));
        accelerazione.setCycleCount(Animation.INDEFINITE);
        accelerazione.play();
    }

    /**
     * sposta la mappa a destra
     * simula la macchina che si sposta a destra
     */
    public void turnRight() {
        mappa.setLayoutX(mappa.getLayoutX() + speed);
    }

    /**
     * sposta la mappa a sinistra
     * simula la macchina che si sposta a sinistra
     */
    public void turnLeft() {
        mappa.setLayoutX(mappa.getLayoutX() - speed);
    }


}
