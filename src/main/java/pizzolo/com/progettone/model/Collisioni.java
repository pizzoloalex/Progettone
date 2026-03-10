package pizzolo.com.progettone.model;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import pizzolo.com.progettone.controller.Controller;

import java.util.Random;
import java.util.stream.DoubleStream;

/**
 * classe che gestisce la creazione di collisioni
 */
public class Collisioni {
    private Controller controller;

    public Collisioni(Controller controller) {
        this.controller = controller;
    }

    /**
     * calcola il range della mappa di X per posizionare l'ostacolo
     *
     * @return un valore casuale di X
     */
    public double posizioneX() {
        //valori random entro i limiti della strada
        Random posizioneRandom = new Random();
        double limiteX = posizioneRandom.nextDouble(controller.getImg_1().getView().getLayoutX(), controller.getImg_1().getView().getLayoutX()
                + controller.getImg_1().getView().getFitWidth() - controller.getOstacolo().getFitWidth());

        return limiteX;
    }

    /**
     * calcola il range della mappa di Y per posizionare l'ostacolo
     *
     * @return un valore casuale di Y
     */
    public double posizioneY() {
        double inizioMappa = controller.getImg_4().getView().getLayoutY(); // valore negativo
        double altezzaTotale = controller.getImg_1().getView().getFitHeight() * 4;
        Random posizioneRandom = new Random();
        return posizioneRandom.nextDouble(inizioMappa, altezzaTotale); // da -2100 circa fino a 0
    }

    public double[] controlloPosizione() {
        Random random = new Random();
        double vicinanzaMin = 100.0;
        boolean troppoVicino;
        double x, y;

        do {
            troppoVicino = false;
            x = posizioneX();
            y = posizioneY();


            // controlla distanza con tutti gli ostacoli già creati
            for (ImageView ostacolo : controller.getOstacoli()) {
                double distanzaX = Math.abs(ostacolo.getLayoutX() - x);
                double distanzaY = Math.abs(ostacolo.getLayoutY() - y);
                if (distanzaX < vicinanzaMin && distanzaY < vicinanzaMin) {
                    troppoVicino = true;
                    break;
                }
            }

        } while (troppoVicino);

        return new double[]{x, y};
    }
}
