package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.scene.shape.Circle;

public class ForestComponent {
    private final int RADIUS = 5;
    protected Circle component = new Circle();

    protected ForestComponent() {
        component.setRadius(RADIUS);
    }

    public Circle getComponent() {
        return component;
    }
}
