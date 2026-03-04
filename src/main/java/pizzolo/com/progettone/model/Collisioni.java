package pizzolo.com.progettone.model;

import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

/**
 * classe che gestisce la creazione di collisioni
 */
public class Collisioni {
    private Rectangle ostacolo;
    Bounds coordinate;
    public Collisioni(Rectangle ostacolo) {
        this.ostacolo = ostacolo;
    }

    /**
     * prende il valore del bordo superiore
     * @return il valore del bordo superiore
     */
    public double getBordoSuperiore(){
        coordinate = ostacolo.getBoundsInParent();
        double bordoSuperiore = coordinate.getMinY();
        return bordoSuperiore;
    }

    /**
     * prende il valore del bordo inferiore
     * @return il valore del bordo inferiore
     */
    public double getBordoInferiore(){
        coordinate = ostacolo.getBoundsInParent();
        double bordoInferiore = coordinate.getMaxY();
        return bordoInferiore;
    }

    /**
     * prende il valore del bordo destro
     * @return il valore del bordo destro
     */
    public double getBordoDestro(){
        coordinate = ostacolo.getBoundsInParent();
        double bordoDestro = coordinate.getMaxX();
        return bordoDestro;
    }

    /**
     * prende il valore del bordo sinistro
     * @return il valore del bordo sinistro
     */
    public double getBordoSinistro(){
        coordinate = ostacolo.getBoundsInParent();
        double bordoSinistro = coordinate.getMinX();
        return bordoSinistro;
    }

}
