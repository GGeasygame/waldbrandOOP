package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.scene.shape.Circle;

import java.util.Random;

public class Forest {
    public int width;
    public int depth;
    private final int treePercentage = 100;
    private ForestComponent[][] forestArray;

    public Forest(int width, int depth) {
        this.width = width;
        this.depth = depth;
        forestArray = new ForestComponent[width][depth];


        for(int i = 0; i < forestArray.length; i++) {
            for(int j = 0; j < forestArray[i].length; j++) {
                Random rnd = new Random();
                int rndNumber = rnd.nextInt(100);
                if (rndNumber < treePercentage) {
                    forestArray[i][j] = new Tree();
                } else {
                    forestArray[i][j] = new Rock();
                }
            }
        }
    }

    public ForestComponent[][] getForestArray() { return forestArray; }

}
