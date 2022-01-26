package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.*;

/*
Developer Note:
Bugs to fix:
- Humus not showing on gp but humus exists
- Same with newly grown trees
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

    private final int INTERVAL = 2000; // Set interval at which fire spreads

    public void onButtonStartSimulation() {
        Forest forest = new Forest(Integer.parseInt(forestWidthTextField.getText()), Integer.parseInt(forestDepthTextField.getText()));
        ForestComponent[][] forestArray = forest.getForestArray();
        arrayToGridPane(forestArray, forestGridPane);
        Timer timer = new Timer();
        timer.schedule(new forestFireTimer(forestArray, forestGridPane), 200, INTERVAL);
    }

    class forestFireTimer extends TimerTask {
        private final ForestComponent[][] forestArray;
        private final GridPane gp;

        forestFireTimer(ForestComponent[][] forestArray, GridPane gp) {
            this.gp = gp;
            this.forestArray = forestArray;
        }
        @Override
        public void run() {
            Platform.runLater(() -> {
                // using this order so there's always a step difference between the processes
                humusToNewTree(forestArray);
                updateGridPane(forestArray, forestGridPane);
                ashesToHumus(forestArray);
                updateGridPane(forestArray, forestGridPane);
                turnToAshes(forestArray);
                spreadFire(forestArray);
                sparkFire(forestArray);
            });
        }
    }

    private void arrayToGridPane(ForestComponent[][] arr, GridPane gp) {
        // Going through every element in the array and adding it to the GridPane
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                gp.add(arr[x][y].component, x, y);
            }
        }
    }

    private void updateGridPane(ForestComponent[][] arr, GridPane gp) {
        gp.getChildren().clear();
        arrayToGridPane(arr, gp);
    }

    private void sparkFire(ForestComponent[][] forestArray) {
        Random rnd = new Random();
        // Going through every element in the array and giving the trees a chance to catch fire
        for (ForestComponent[] forestComponents : forestArray) {
            for (ForestComponent forestComponent : forestComponents) {
                if (rnd.nextInt(100) < Integer.parseInt(sparkTextField.getText())
                        && forestComponent instanceof Tree && !(((Tree) forestComponent).isBurning) && !(((Tree) forestComponent).isAsh)) {
                    ((Tree) forestComponent).sparkFire();
                }
            }
        }
    }

    private void spreadFire(ForestComponent[][] forestArray) {
        // First putting trees in ArrayList and then burning so trees that just got burned can't
        // burn neighbouring trees during the same tick (because of for-loop)

        ArrayList<Tree> treesToSetOnFire = new ArrayList<>();

        for (int x = 0; x < forestArray.length; x++) {
            for (int y = 0; y < forestArray[x].length; y++) {
                ForestComponent subjectComponent = forestArray[x][y];
                // check if burning Tree
                if (!(subjectComponent instanceof Tree) || !(((Tree) subjectComponent).isBurning)) continue;

                ForestComponent[] neighbouringComponents = Forest.getNeighbours(forestArray, x, y);

                // Cycle through components and set non-burning trees on fire
                for (ForestComponent neighbouringComponent : neighbouringComponents) {
                    if (neighbouringComponent instanceof Tree && !(((Tree) neighbouringComponent).isBurning) && !(((Tree) neighbouringComponent).isAsh)) {
                        treesToSetOnFire.add((Tree) neighbouringComponent);
                    }
                }
            }
        }
        for (Tree tree : treesToSetOnFire) {
            tree.sparkFire();
        }
    }

    private void turnToAshes(ForestComponent[][] forestArray) {
        for (int x = 0; x < forestArray.length; x++) {
            for (int y = 0; y < forestArray[x].length; y++) {
                // Check if neither tree nor burning
                if (!(forestArray[x][y] instanceof Tree) || !(((Tree) forestArray[x][y]).isBurning)) continue;

                boolean willTurnToAsh = true;
                // Check if neighbours are all burnt, if yes proceed to turning tree into ashes
                ForestComponent[] neighbours = Forest.getNeighbours(forestArray, x, y);
                for (ForestComponent neighbour : neighbours) {
                    if (!(neighbour instanceof Tree)) continue;
                    if (!(((Tree) neighbour).isBurning) && !(((Tree) neighbour).isAsh)) {
                        willTurnToAsh = false;
                        break;
                    }
                }
                if (willTurnToAsh)
                    ((Tree) forestArray[x][y]).turnToAsh();
            }
        }
    }

    private void ashesToHumus(ForestComponent[][] forestArray) {
        for (int x = 0; x < forestArray.length; x++) {
            for (int y = 0; y < forestArray[x].length; y++) {
                if (!(forestArray[x][y] instanceof Tree) || !(((Tree) forestArray[x][y]).isAsh)) continue;

                forestArray[x][y] = new Humus();
            }
        }
    }

    private void humusToNewTree(ForestComponent[][] forestArray) {
        for (int x = 0; x < forestArray.length; x++) {
            for (int y = 0; y < forestArray[x].length; y++) {
                if (!(forestArray[x][y] instanceof Humus)) continue;

                // Make sure only a percentage of the humus turns into trees
                Random rnd = new Random();
                if (rnd.nextInt(100) < Integer.parseInt(growthTextField.getText()))
                    forestArray[x][y] = new Tree();
            }
        }
    }
}