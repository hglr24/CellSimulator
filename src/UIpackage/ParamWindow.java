package UIpackage;

import Configuration.SimulationInfo;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Dimension;
import java.util.ArrayList;

class ParamWindow extends Stage {

    private Scene myScene;
    private BorderPane myBorderPane;
    private SimulationInfo mySimInfo;
    private ArrayList<TextField> myFieldList = new ArrayList<>();
    private ArrayList<Double> myOldVals = new ArrayList<>();
    private ArrayList<Double> myNewVals = new ArrayList<>();
    private static final Dimension DEFAULT_SIZE = new Dimension(200, 120);
    private static final String DEFAULT_STYLESHEET = "/resources/default.css";

    ParamWindow(SimulationInfo simInfo) {
        mySimInfo = simInfo;
        myBorderPane = new BorderPane();
        myScene = new Scene(myBorderPane, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        myBorderPane.setBottom(drawButtons());
        myBorderPane.setCenter(drawFieldGrid());
        this.setTitle("Edit Parameters");
        this.setScene(myScene);
        this.setResizable(false);
        this.show();
    }

    private Node drawButtons() {
        HBox controlBox = new HBox();
        controlBox.getStyleClass().add("controls");
        Button applyButton = new Button("Apply");
        applyButton.setOnAction(event -> applyParams());
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> cancel());
        controlBox.getChildren().add(applyButton);
        controlBox.getChildren().add(cancelButton);
        return controlBox;
    }

    private Node drawFieldGrid() {
        GridPane paramGrid = new GridPane();
        double[] paramVals = mySimInfo.getParameters();
        int row = 0;
        for (double v : paramVals) {
            myOldVals.add(v);
            myNewVals.add(v);
            paramGrid.add(drawParamField("Parameter " + (row + 1), v), 0, row); //TODO get real labels
            row++;
        }
        return paramGrid;
    }

    private Node drawParamField(String label, double startVal) {
        HBox fieldBox = new HBox();
        fieldBox.getStyleClass().add("legend-item");
        Text paramLabel = new Text(label);
        TextField paramField = new TextField();
        paramField.setText(Double.toString(startVal));
        myFieldList.add(paramField);
        fieldBox.getChildren().addAll(paramLabel, paramField);
        return fieldBox;
    }

    private void applyParams() {
        double[] rtn = new double[myNewVals.size()];
        for (TextField tf : myFieldList) {
            myNewVals.set(myFieldList.indexOf(tf), Double.parseDouble(tf.getText()));
            rtn[myFieldList.indexOf(tf)] = Double.parseDouble(tf.getText());
        }
        System.out.println(myNewVals);
        mySimInfo.getSimType().getRules().setParameters(rtn);
        this.close();
    }

    private void cancel() {
        this.close();
    }
}
