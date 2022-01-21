package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.scene.shape.Circle;

public class ForestComponent {
    protected Circle component = new Circle();

    protected ForestComponent() {
        component.setRadius(5);
    }

    public Circle getComponent() {
        return component;
    }
}
