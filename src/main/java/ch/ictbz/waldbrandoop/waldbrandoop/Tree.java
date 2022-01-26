package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.scene.paint.Color;

public class Tree extends ForestComponent {
    public boolean isBurning;
    public static String colourHex;

    public Tree() {
        isBurning = false;
        colourHex = "#014704";
        Color color = Color.web(colourHex);
        component.setFill(color);
    }

    public void sparkFire() {
        isBurning = true;
        colourHex = "#ba3a13";
        Color color = Color.web(colourHex);
        component.setFill(color);
    }
}
