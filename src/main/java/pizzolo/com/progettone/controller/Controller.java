package pizzolo.com.progettone.controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pizzolo.com.progettone.model.Mappa;
import pizzolo.com.progettone.model.Movimento;

import java.util.Map;

/**
 * la mappa si muove e si aggiorna ad ogni movimento della macchina
 * la macchina sta ferma e fa gli effetti per far sembrare che si muove
 */




//TODO inserire immagine di collisione e aggiungerlo nel array
//TODO scrivere meglio il codice



public class Controller {
//    @FXML
//    private VBox vBox;
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
    private Mappa collisione;
    //variabile del movimento
    private Movimento move;
    //variabili per la gestione dei click
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;


    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    double centro;


    //TODO aggiungere al immagini alla strada per le collisioni
    /*
    per le collisioni inserisco l'immagine della collisione singola posizionando la macchine in determinate posizioni.
    rimpicciolendo la macchina, mettendo piu immagini della stessa sequenza.
    tagliare l.immagine e rimuovere lo sfondo
     */
    //TODO pulire eventuale codice
    /**
     * crea le immagini iniziali
     * gestisce il layot delle immagini
     */
    public void initialize() {
        //creazione delle immagini iniziali
        img_1 = new Mappa(0, "/pizzolo/com/progettone/images/continuo_strada.png");
        img_2 = new Mappa(1, "/pizzolo/com/progettone/images/continuo_strada.png");
        img_3 = new Mappa(2, "/pizzolo/com/progettone/images/continuo_strada.png");
        img_4 = new Mappa(3, "/pizzolo/com/progettone/images/continuo_strada.png");
        collisione = new Mappa(4,"/pizzolo/com/progettone/images/collisione_macchina.jpg");

        //posiziona le immagini una sotto l'altra
        img_1.getView().setLayoutY(0);
        img_2.getView().setLayoutY(-img_1.getView().getFitHeight());
        img_3.getView().setLayoutY(img_2.getView().getLayoutY() - img_3.getView().getFitHeight());
        img_4.getView().setLayoutY(img_3.getView().getLayoutY() - img_4.getView().getFitHeight());

        centro = (mappa.getPrefWidth() - img_1.getView().getFitWidth()) / 2;
        img_1.getView().setLayoutX(centro);
        img_2.getView().setLayoutX(centro);
        img_3.getView().setLayoutX(centro);
        img_4.getView().setLayoutX(centro);

        //imposta la larghezza del pane quanto la larghezza del immagine
        mappa.setPrefWidth(img_1.getView().getFitWidth());
        mappa.getChildren().addAll(img_1.getView(), img_2.getView(), img_3.getView(), img_4.getView());
        macchina.toFront();

        //inserisco macchina
        Image img_macchina = new Image(getClass().getResource("/pizzolo/com/progettone/images/macchina.png").toExternalForm());
        macchina.setImage(img_macchina);
        double centroMacchina = (mappa.getPrefWidth() - macchina.getFitWidth()) / 2;
        macchina.setFitWidth(img_macchina.getWidth());
        macchina.setLayoutX(centroMacchina);
        macchina.setLayoutY(mappa.getPrefHeight() - macchina.getFitHeight() - 20);


        Mappa[] immagini = {img_1, img_2, img_3, img_4};
        move = new Movimento(mappa, immagini, macchina, this);


        //aspetta che la scena sia carica
        schermo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::movimentoOnKeyPressed);
                newScene.setOnKeyReleased(this::movimentoOnKeyRelased);
            }
        });
    }

    /**
     * movimento della strada per simulare movimento della macchina con animazione
     * W = avanti, una volta partito non si ferma, tranne allo scontro
     * A = sinstra
     * D = destra
     * @param event input della tastiera
     */
    public void movimentoOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> {
                upPressed = true;
                move.accelera();
                move.start();
            }
            case A, LEFT -> leftPressed = true;
            case D, RIGHT -> rightPressed = true;
            default -> System.out.println("Testo non valido!!");
        }
    }

    /**
     * gestisce il rilascio dei tasti
     * @param event rilascio del tasto premuto
     */
    public void movimentoOnKeyRelased(KeyEvent event) {
        switch (event.getCode()) {
            //gestione del click
            case W, UP -> upPressed = false;
            case A, LEFT -> leftPressed = false;
            case D, RIGHT -> rightPressed = false;
            default -> System.out.println("Tasto non valido!!");
        }
    }

}
