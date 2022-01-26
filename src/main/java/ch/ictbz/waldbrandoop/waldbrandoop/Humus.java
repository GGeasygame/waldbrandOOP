package ch.ictbz.waldbrandoop.waldbrandoop;


import javafx.scene.paint.Color;

public class Humus extends  ForestComponent {
    public static String colourHex;

    public Humus() {
        colourHex = "#dff026";
        Color color = Color.web(colourHex);
        component.setFill(color);
    }

}
