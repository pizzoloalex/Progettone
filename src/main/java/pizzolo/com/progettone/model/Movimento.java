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
import pizzolo.com.progettone.controller.Controller;

import javax.swing.*;

/**
 * gestisce i movimenti della macchina
 */

//TODO gestire collisioni

public class Movimento extends AnimationTimer {

    private double speedBase = 6;
    private final Pane mappa;
    private Mappa[] immagini;
    private Timeline accelerazione;//accelerazione per la macchina
    private ImageView macchina;
    private Controller controller;
    private double speed = speedBase;

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

//        if(!move) return;
        //movimento continuo e fluido
        if (controller.isLeftPressed()) turnLeft();
        if (controller.isRightPressed()) turnRight();

        collisione();
    }

    /**
     * ogni 15 secondi la velocita aumenta
     */
    public void accelera() {
        accelerazione = new Timeline(new KeyFrame(Duration.seconds(15), actionEvent -> {
            this.speedBase += 5;
        }
        ));
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
//        System.out.println(macchina.getBoundsInParent().getCenterX());
//        System.out.println(larghezzaReale);
        double nuovaPosX = macchina.getLayoutX() + speed;
        double limite = mappa.getPrefWidth() - larghezzaReale;
        if (nuovaPosX > limite) {
            nuovaPosX = limite;
        }
//        collisione();
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

    /**
     * @return bordo sinistro della strada
     */
    private double getBordoSinistroStrada() {
        return immagini[0].getView().getLayoutX();
    }

    /**
     * @return il bordo destro della strada
     */
    private double getBordoDestroStrada() {
        return immagini[0].getView().getLayoutX() + immagini[0].getView().getFitWidth();
    }

    /**
     * decrementa velocita se tocca bordo
     * aumenta velocita se torna in strada
     */
    public void collisione() {
        Bounds macchina = controller.getBoundsMacchina();
        double bordoSx = getBordoSinistroStrada();
        double bordoDx = getBordoDestroStrada();

        double tolleranza = 10; // pixel di margine dai bordi

        boolean vicinoBordo = macchina.getMinX() < bordoSx + tolleranza ||
                macchina.getMaxX() > bordoDx - tolleranza;

        if (vicinoBordo) {
            speed = Math.max(1, speed * 0.95);
        } else {
            speed = Math.min(speedBase, speed + 0.05);
        }
    }
    //todo gestire immagini con collisioni

}
