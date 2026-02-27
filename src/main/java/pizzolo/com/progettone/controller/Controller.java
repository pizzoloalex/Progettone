package pizzolo.com.progettone.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
//    @FXML
//    private VBox mappa;//mostra la mappa
    @FXML
    private ImageView macchina;//mostra la macchina

    //variabili per immagini
    private Mappa img_1;
    private Mappa img_2;

    /**
     * crea le immagini iniziali
     */
    public void initialize() {
        img_1 = new Mappa(0, "/pizzolo/com/progettone/images/continuo_strada.png");
        img_2 = new Mappa(1, "/pizzolo/com/progettone/images/continuo_strada.png");
        schermo.getChildren().addAll(img_1.getView(), img_2.getView());

        //aspetta che la scena sia carica
        schermo.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::movimento);
            }
        });
    }

    public void movimento(KeyEvent event) {
        double step = 10;
//        System.out.println("Premuto" + event.getCode());
        if (event.getCode() == KeyCode.W) {
            img_1.getView().setLayoutY(img_1.getView().getLayoutY() - step);
            img_2.getView().setLayoutY(img_2.getView().getLayoutY() - step);
        }
    }

}
