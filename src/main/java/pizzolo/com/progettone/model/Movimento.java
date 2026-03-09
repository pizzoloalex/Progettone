package pizzolo.com.progettone.model;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;
import pizzolo.com.progettone.controller.Controller;

import javax.swing.*;

/**
 * gestisce i movimenti della macchina
 */

//TODO gestire collisioni

public class Movimento extends AnimationTimer {

    private double speed = 6;
    private final Pane mappa;
    private final Mappa[] immagini;
    private Timeline accelerazione;//accelerazione per la macchina
    private final ImageView macchina;
    private final Controller controller;
    private Collisioni collisione;
    private Rectangle rect;

    public Movimento(Pane mappa, Mappa[] immagini, ImageView macchina, Controller controller) {
        this.mappa = mappa;
        this.immagini = immagini;
        this.macchina = macchina;
        this.controller = controller;
    }

    /**
     * gestisce i movimenti della macchina ad ogni frame
     * quando un immagine esce dal inquadraura (Pane) viene messa sotto
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
        //posiziona il rettangolo
        for (Rectangle rect : controller.getOstacoli()) {
            rect.setY(rect.getY() + speed);
            // quando esce dallo schermo, riportalo sopra
            if (rect.getY() >= mappa.getHeight()) {
                rect.setY(rect.getY() - immagini[0].getView().getFitHeight() * immagini.length); // torna sopra
            }
        }
        if (controller.isLeftPressed()) turnLeft();
        if (controller.isRightPressed()) turnRight();

        collisione();

    }

    /**
     * ogni 15 secondi la velocita aumenta
     */
    public void accelera() {
        accelerazione = new Timeline(new KeyFrame(Duration.seconds(15), actionEvent -> {
            this.speed += 5;
        }));
        accelerazione.setCycleCount(Animation.INDEFINITE);
        accelerazione.play();
    }

    /**
     * sposta la macchina a destra
     * calcolando la posizione iniziale
     */
    private void turnRight() {
        double speed = 5;
        double larghezzaReale = macchina.getBoundsInParent().getWidth();
        double nuovaPosX = macchina.getLayoutX() + speed;
        double limite = mappa.getPrefWidth() - larghezzaReale;
        if (nuovaPosX > limite) {
            nuovaPosX = limite;
        }
        macchina.setLayoutX(nuovaPosX);
    }

    /**
     * sposta la macchina a sinistra
     */
    private void turnLeft() {
        double speed = 5;
        double nuovaPosX = macchina.getLayoutX() - speed;//ultima posizione possibile
        if (nuovaPosX < 0) {
            nuovaPosX = 0;
        }
        macchina.setLayoutX(nuovaPosX);
    }

    //todo gestire immagini con collisioni

    /**
     * gestisce la collisione della macchina controllando i bordi
     */
    public void collisione() {
        for (Rectangle rect : controller.getOstacoli()) {
            if (macchina.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                System.out.println("Collisione rilevata");
                this.stop();
            }
        }
    }
}


