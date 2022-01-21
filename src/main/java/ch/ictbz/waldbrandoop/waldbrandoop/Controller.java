package ch.ictbz.waldbrandoop.waldbrandoop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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

    public void onButtonStartSimulation() {
        Forest forest = new Forest(Integer.parseInt(forestWidthTextField.getText()), Integer.parseInt(forestDepthTextField.getText()));
        arrayToGridPane(forest.returnForestArray(), forestGridPane);

    }

    private void arrayToGridPane(Object[][] arr, GridPane gp) {
        growthTextField.setText(arr[0][0].toString());
        for(int i = 0 ; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                gp.add(arr[i][j].getComponent(), j, i);
            }
        }
    }
}