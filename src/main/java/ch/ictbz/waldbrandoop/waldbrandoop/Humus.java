package ch.ictbz.waldbrandoop.waldbrandoop;


import javafx.scene.paint.Color;

public class Humus extends  ForestComponent {
    public static final String colourHex = "#5c4949";

    public Humus() {
        Color color = Color.web(colourHex);
        component.setFill(color);
    }

}
