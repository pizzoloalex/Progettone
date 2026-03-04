package pizzolo.com.progettone.model;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pizzolo.com.progettone.controller.Controller;

/**
 * gestisce i movimenti della macchina
 */

    //TODO gestire collisioni

public class Movimento extends AnimationTimer {

    private double speed = 6;
    private final Pane mappa;
    private Mappa[] immagini;
    private Timeline accelerazione;//accelerazione per la macchina
    private ImageView macchina;
    private Controller controller;
//    boolean move;

    public Movimento(Pane mappa, Mappa[] immagini, ImageView macchina, Controller controller) {
        this.mappa = mappa;
        this.immagini = immagini;
        this.macchina = macchina;
        this.controller = controller;
//        move = true;
    }
//
//    public void setMove(boolean move) {
//        this.move = move;
//    }

    /**
     * gestisce i movimenti della macchina ad ogni frame
     * quando un immagine esce dal inquadraura (Pane) viene messa sotto
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
    }

    /**
     * ogni 15 secondi la velocita aumenta
     */
    public void accelera() {
        accelerazione = new Timeline(new KeyFrame(Duration.seconds(15), actionEvent -> this.speed++));
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
        if (nuovaPosX > limite){
//            collisione();
//            this.speed = 2;
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
    public void collisione(){
        Collisioni collisione = new Collisioni(new Rectangle(macchina.getX(),macchina.getY(), macchina.getFitWidth(),macchina.getFitHeight()));
        System.out.println("Bordo superiore: " + collisione.getBordoSuperiore());
    }

}
