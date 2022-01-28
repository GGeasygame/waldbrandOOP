package ch.ictbz.waldbrandoop.waldbrandoop;

import java.util.Random;

public class Forest {
    public int width;
    public int depth;
    private final int TREEPERCENTAGE = 80;      // Change this value for a different tree/stone-ratio (% trees)
    private final ForestComponent[][] forestArray;

    public Forest(int width, int depth) {
        this.width = width;
        this.depth = depth;
        forestArray = new ForestComponent[width][depth];

        // loop through the array and assign every index to either a tree or a stone.
        for(int x = 0; x < forestArray.length; x++) {
            for(int y = 0; y < forestArray[x].length; y++) {
                Random rnd = new Random();
                int rndNumber = rnd.nextInt(100);
                if (rndNumber < TREEPERCENTAGE) {
                    forestArray[x][y] = new Tree();
                } else {
                    forestArray[x][y] = new Rock();
                }
                forestArray[x][y].createImageView();
            }
        }
    }

    public ForestComponent[][] getForestArray() { return forestArray; }

    public static ForestComponent[] getNeighbours(ForestComponent[][] forestArray, int x, int y) {
        // get neighbouring forestComponents and check for borders
        ForestComponent[] neighbouringComponents = new ForestComponent[4];
        // left
        if (x != 0)
            neighbouringComponents[0] = forestArray[x - 1][y];
        // bottom
        if (y != forestArray[x].length - 1)
            neighbouringComponents[1] = forestArray[x][y + 1];
        // right
        if (x != forestArray.length - 1)
            neighbouringComponents[2] = forestArray[x + 1][y];
        // top
        if (y != 0)
            neighbouringComponents[3] = forestArray[x][y - 1];

        return neighbouringComponents;
    }
}
