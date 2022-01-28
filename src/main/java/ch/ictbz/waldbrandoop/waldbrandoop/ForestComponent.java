package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.InputStream;

public class ForestComponent {
    protected String component = "";
    private ImageView imageView = new ImageView();

    public ForestComponent() {
        // Change width and height of the ForestComponents
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void createImageView() {
        try {
            InputStream stream = new FileInputStream(component);
            Image image = new Image(stream);
            imageView.setImage(image);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
