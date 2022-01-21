package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.scene.paint.Color;

public class Tree extends ForestComponent {
    public boolean isBurning = false;
    public static final String colourHex = "#014704";

    public Tree() {
        Color color = Color.web(colourHex);
        component.setFill(color);
    }

    public void sparkFire() {

    }
}
