package pizzolo.com.progettone.model;

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
    private Timeline accelerazione; //accelerazione per la macchina

    public Movimento(Pane mappa) {
        this.mappa = mappa;
    }

    /**
     * gestisce i movimenti della macchina ad ogni frame
     * @param l
     */
    @Override
    public void handle(long l) {
        mappa.setLayoutY(mappa.getLayoutY() + speed);
        //TODO inizializzare la logica delle collisioni
    }

    /**
     * ogni 15 secondi la velocita aumenta
     */
    public void accelera(){
        accelerazione = new Timeline(new KeyFrame(Duration.seconds(15), actionEvent -> this.speed += 10));
    }

    /**
     * sposta la mappa a destra
     * simula la macchina che si sposta a destra
     */
    public void turnRight(){
        mappa.setLayoutX(mappa.getLayoutX() + speed);
    }

    /**
     * sposta la mappa a sinistra
     * simula la macchina che si sposta a sinistra
     */
    public void turnLeft(){
        mappa.setLayoutX(mappa.getLayoutX() - speed);
    }


}
