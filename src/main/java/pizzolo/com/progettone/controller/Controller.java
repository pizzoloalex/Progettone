package pizzolo.com.progettone.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pizzolo.com.progettone.model.Mappa;

/**
 * la mappa si muove e si aggiorna ad ogni movimento della macchina
 * la macchina sta ferma e fa gli effetti per far sembrare che si muove
 */

public class Controller {
    @FXML
    private VBox vBox;
    @FXML
    private StackPane schermo;//mostra tutto il gioco
    @FXML
    private Pane mappa;//mostra la mappa
    @FXML
    private ImageView macchina;//mostra la macchina

    //variabili per immagini
    private Mappa img_1;
    private Mappa img_2;
    private Mappa img_3;
    private Mappa img_4;

    /**
     * crea le immagini iniziali
     */
    public void initialize() {
        img_1 = new Mappa(0, "/pizzolo/com/progettone/images/continuo_strada.png");
        img_2 = new Mappa(1, "/pizzolo/com/progettone/images/continuo_strada.png");
        img_3 = new Mappa(2, "/pizzolo/com/progettone/images/continuo_strada.png");
        img_4 = new Mappa(3, "/pizzolo/com/progettone/images/continuo_strada.png");

        //TODO calcolare meglio il posizionamento delle immagini
        //posiziona le immagini una sotto l'altra
        img_1.getView().setLayoutY(0);
        img_2.getView().setLayoutY(-img_1.getView().getFitHeight());
        img_3.getView().setLayoutY(img_2.getView().getLayoutY() - img_3.getView().getFitHeight());
        img_4.getView().setLayoutY(img_3.getView().getLayoutY() - img_4.getView().getFitHeight());
        //TODO trovare modo di scrivere meno immagini, ad esempio con ciclo for, ripetizione della stessa immagine
        double centro = (mappa.getPrefWidth() - img_1.getView().getFitWidth()) / 2;
        img_1.getView().setLayoutX(centro);
        img_2.getView().setLayoutX(centro);
        img_3.getView().setLayoutX(centro);
        img_4.getView().setLayoutX(centro);
        mappa.getChildren().addAll(img_1.getView(), img_2.getView(), img_3.getView(), img_4.getView());


        //aspetta che la scena sia carica
        schermo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::movimento);
            }
        });
    }

    /**
     * movimento della strada per simulare movimento della macchina
     * W = avanti
     * S = indietro
     * A = sinstra
     * D = destra
     *
     * @param event input della tastiera
     */
    public void movimento(KeyEvent event) {
        double step = 30;//velocita con cui si muove
        ImageView immaggine1 = img_1.getView();
        ImageView immaggine2 = img_2.getView();
        ImageView immaggine3 = img_3.getView();
        ImageView immaggine4 = img_4.getView();
        switch (event.getCode()) {
            case W, UP:
                //simulazione macchina avanti
                immaggine1.setLayoutY(immaggine1.getLayoutY() + step);
                immaggine2.setLayoutY(immaggine2.getLayoutY() + step);
                immaggine3.setLayoutY(immaggine3.getLayoutY() + step);
                immaggine4.setLayoutY(immaggine4.getLayoutY() + step);
                loopInfinito();
                break;
            case S, DOWN:
                //simulazione macchina indietro (rallentamento)
                step = 15;
                immaggine1.setLayoutY(immaggine1.getLayoutY() - step);
                immaggine2.setLayoutY(immaggine2.getLayoutY() - step);
                immaggine3.setLayoutY(immaggine3.getLayoutY() - step);
                immaggine4.setLayoutY(immaggine4.getLayoutY() - step);
                loopInfinito();
                break;
            case A, LEFT:
                immaggine1.setLayoutX(immaggine1.getLayoutX() - step);
                immaggine2.setLayoutX(immaggine2.getLayoutX() - step);
                immaggine3.setLayoutX(immaggine3.getLayoutX() - step);
                immaggine4.setLayoutX(immaggine4.getLayoutX() - step);
                break;
            case D, RIGHT:
                immaggine1.setLayoutX(immaggine1.getLayoutX() + step);
                immaggine2.setLayoutX(immaggine2.getLayoutX() + step);
                immaggine3.setLayoutX(immaggine3.getLayoutX() + step);
                immaggine4.setLayoutX(immaggine4.getLayoutX() + step);
                break;
            default:
                System.out.println("Input non valido");
        }
    }

    /**
     * ogni volta che la strada esce dallo schermo ricompare dal alto
     */
    private void loopInfinito() {
        double altezzaMappa = mappa.getHeight();//altezza della mappa
        //altezza delle immagini
        double altezzaImmagine1 = img_1.getView().getLayoutY();
        double altezzaImmagine2 = img_2.getView().getLayoutY();
        double altezzaImmagine3 = img_3.getView().getLayoutY();
        double altezzaImmagine4 = img_4.getView().getLayoutY();
        double altezza = img_1.getView().getFitHeight(); //altezza del immagine da reimpostare

        if (altezzaImmagine1 > altezzaMappa) {
            img_1.getView().setLayoutY(-altezza);
        }
        if (altezzaImmagine2 > altezzaMappa) {
            img_2.getView().setLayoutY(-altezza);
        }
        if (altezzaImmagine3 > altezzaMappa) {
            img_3.getView().setLayoutY(-altezza);
        }
        if (altezzaImmagine4 > altezzaMappa) {
            img_4.getView().setLayoutY(-altezza);
        }
    }


}
