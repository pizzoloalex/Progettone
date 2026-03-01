package pizzolo.com.progettone.model;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * gestisce i movimenti della macchina
 */

public class Movimento extends AnimationTimer {

    private double speed = 10;
    private final Pane mappa;
    private Mappa[] immagini;
    private Timeline accelerazione;//accelerazione per la macchina
    private ImageView macchina;

    public Movimento(Pane mappa, Mappa[] immagini, ImageView macchina) {
        this.mappa = mappa;
        this.immagini = immagini;
        this.macchina = macchina;
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
        accelerazione = new Timeline(new KeyFrame(Duration.seconds(30), actionEvent -> this.speed++));
        accelerazione.setCycleCount(Animation.INDEFINITE);
        accelerazione.play();
    }

    /**
     * sposta la macchina a destra
     */
    public void turnRight() {
        double nuovaPosX = macchina.getLayoutX() + speed;//calcolo prima la nuova posizione
        double limite = mappa.getWidth() - macchina.getFitWidth();//ultima posizione possibile della X per la macchina
        //gestiso il limite
        if (nuovaPosX > limite){
            nuovaPosX = limite;
        }
        macchina.setLayoutX(nuovaPosX);
    }

    /**
     * sposta la macchina a sinistra
     */
    public void turnLeft() {
        //TODO
        double nuovaPosX = macchina.getLayoutX() - speed;
        if (nuovaPosX < 0){
            nuovaPosX = 0;
        }
        macchina.setLayoutX(nuovaPosX);
    }


}
