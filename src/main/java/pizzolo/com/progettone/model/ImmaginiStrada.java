package pizzolo.com.progettone.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImmaginiStrada {
    private int id;
    private Image strada;
    private ImageView view;

    public ImmaginiStrada(int id, String urlImg) {
        this.id = id;
        loadImages(urlImg, id);
    }

    /**
     * ogni id e per distinguere le immagini
     * @param url del immagine
     * @param id di ogni immagine diversa
     */
    private void loadImages(String url, int id) {
        this.id = id;
        this.strada = new Image(getClass().getResource(url).toExternalForm());
        this.view.setImage(this.strada);
    }
}
