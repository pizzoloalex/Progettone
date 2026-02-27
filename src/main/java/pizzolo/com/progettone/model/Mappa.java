package pizzolo.com.progettone.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.InvocationTargetException;


public class Mappa {
    private int id;
    private Image strada;
    private ImageView view;

    public Mappa(int id, String urlImg)  {
        this.id = id;
        loadImages(urlImg, id);
    }
    /**
     * ogni id e per distinguere le immagini
     * @param url del immagine
     * @param id di ogni immagine diversa
     */
    private void loadImages(String url, int id)  {
        this.id = id;
        if (url == "" || url == null){
            System.out.println("Percorso non valido");
        }else{
            this.strada = new Image(getClass().getResource(url).toExternalForm());
            this.view = new ImageView(this.strada);
            this.view.setFitHeight(400);
            this.view.setFitWidth(200);
        }
    }

    public int getId() {
        return id;
    }

    public Image getStrada() {
        return strada;
    }

    public ImageView getView() {
        return view;
    }
}
