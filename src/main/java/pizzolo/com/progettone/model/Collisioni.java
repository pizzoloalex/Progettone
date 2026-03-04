package pizzolo.com.progettone.model;

import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

/**
 * classe che gestisce la creazione di collisioni
 */
public class Collisioni {
    private Rectangle ostacolo;
    public Collisioni(Rectangle ostacolo) {
        this.ostacolo = ostacolo;
    }

    /**
     * prende il valore del bordo superiore
     * @return il valore del bordo superiore
     */
    public double getBordoSuperiore(){
        Bounds coordinate = ostacolo.getBoundsInParent();
        double bordoSuperiore = coordinate.getMinY();
        return bordoSuperiore;
    }

//    public double getBordoInferiore(){
//
//    }
}
