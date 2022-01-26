package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
Developer Note:
Bugs to fix:
- Fire is not spreading correctly
 */


public class Controller {
    @FXML
    private GridPane forestGridPane;
    @FXML
    private TextField forestWidthTextField;
    @FXML
    private TextField forestDepthTextField;
    @FXML
    private TextField growthTextField;
    @FXML
    private TextField sparkTextField;

    private final int INTERVAL = 1000; // Set interval at which fire spreads

    public void onButtonStartSimulation() {
        Forest forest = new Forest(Integer.parseInt(forestWidthTextField.getText()), Integer.parseInt(forestDepthTextField.getText()));
        ForestComponent[][] forestArray = forest.getForestArray();
        arrayToGridPane(forestArray, forestGridPane);
        Timer timer = new Timer();
        timer.schedule(new forestFireTimer(forest, forestArray), 200, INTERVAL);

    }
    class forestFireTimer extends TimerTask {
        private Forest forest;
        private ForestComponent[][] forestArray;
        forestFireTimer(Forest forest, ForestComponent[][] forestArray) {
            this.forest = forest;
            this.forestArray = forestArray;
        }
        public void run() {
            sparkFire(forestArray);
            spreadFire(forestArray);
        }
    }

    private void arrayToGridPane(ForestComponent[][] arr, GridPane gp) {
        // Going through every element in the array and adding it to the GridPane
        for(int i = 0 ; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                gp.add(arr[i][j].component, i, j, 1, 1);
            }
        }
    }

    private void sparkFire(ForestComponent[][] forestArray) {
        Random rnd = new Random();
        // Going through every element in the array and giving the trees a chance to catch fire
        for (ForestComponent[] forestComponents : forestArray) {
            for (ForestComponent forestComponent : forestComponents) {
                if (rnd.nextInt(100) < Integer.parseInt(sparkTextField.getText())
                        && forestComponent instanceof Tree && !(((Tree) forestComponent).isBurning)) {
                    ((Tree) forestComponent).sparkFire();
                }
            }
        }
    }

    private void spreadFire(ForestComponent[][] forestArray) {
        for(int i = 0; i < forestArray.length; i++) {
            for(int j = 0; j <forestArray[i].length; j++) {
                ForestComponent subjectComponent = forestArray[i][j];
                // check if burning Tree
                if (!(subjectComponent instanceof Tree) || !(((Tree) subjectComponent).isBurning)) break;

                // get neighbouring forestComponents
                ForestComponent[] neighbouringComponents = new ForestComponent[4];
                // top
                if (i != 0)
                    neighbouringComponents[0] = forestArray[i-1][j];
                // right
                if (j != forestArray[i].length-1)
                    neighbouringComponents[1] = forestArray[i][j+1];
                // bottom
                if (i != forestArray.length-1)
                    neighbouringComponents[2] = forestArray[i+1][j];
                // left
                if (j != 0)
                    neighbouringComponents[3] = forestArray[i][j-1];

                // Cycle through components and set non-burning trees on fire
                for (ForestComponent neighbouringComponent : neighbouringComponents) {
                    if (neighbouringComponent instanceof Tree && !(((Tree) neighbouringComponent).isBurning)) {
                        ((Tree) neighbouringComponent).sparkFire();
                    }
                }
            }
        }
    }
}