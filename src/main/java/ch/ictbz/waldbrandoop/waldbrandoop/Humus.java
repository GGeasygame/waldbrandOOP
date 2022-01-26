package ch.ictbz.waldbrandoop.waldbrandoop;


import javafx.scene.paint.Color;

public class Humus extends  ForestComponent {
    public static String colourHex;

    public Humus() {
        colourHex = "#421e11";
        Color color = Color.web(colourHex);
        component.setFill(color);
    }

}
