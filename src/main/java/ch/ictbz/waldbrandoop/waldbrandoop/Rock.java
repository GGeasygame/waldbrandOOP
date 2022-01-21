package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.scene.paint.Color;

public class Rock extends ForestComponent{
    public static final String colourHex = "#646965";

    public Rock() {
        Color color = Color.web(colourHex);
        component.setFill(color);
    }

}
