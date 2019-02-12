package UIpackage;

import Configuration.CheckParameters;
import Configuration.SimulationInfo;
import Simulation.RuleSet;
import Simulation.SimulationType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ParamWindow extends Stage {

    private RuleSet myRules;
    private Map<String, Double> myParams;
    private Map<String, Double> myOldVals;
    private SimulationType mySimType;
    private HashMap<TextField, String> myFieldMap = new HashMap<>();
    private static final String DEFAULT_STYLESHEET = "/resources/default.css";

    ParamWindow(SimulationInfo simInfo) {
        myRules = simInfo.getType().getRules();
        mySimType = simInfo.getType();
        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        bp.setBottom(drawButtons());
        bp.setCenter(drawFieldGrid());
        this.sizeToScene();
        this.setTitle("Edit Parameters");
        this.setScene(scene);
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
        paramGrid.getStyleClass().add("legend-item");
        paramGrid.setHgap(10);
        myParams = myRules.getParameters();
        myOldVals = new HashMap<>(myParams);
        int row = 0;
        for (String label : myParams.keySet()) {
            drawParamField(paramGrid, label, myParams.get(label), row);
            row++;
        }
        return paramGrid;
    }

    private void drawParamField(GridPane paramGrid, String label, double startVal, int row) {
        Text paramLabel = new Text(label);
        TextField paramField = new TextField();
        paramField.setText(Double.toString(startVal));
        myFieldMap.put(paramField, label);
        paramGrid.add(paramLabel, 0, row);
        paramGrid.add(paramField, 1, row);
    }

    private void applyParams() {
        double[] toCheck = new double[myParams.keySet().size()];
        CheckParameters checker = new CheckParameters();
        ArrayList<TextField> indexedFieldList = new ArrayList<>(myFieldMap.keySet());
        for (TextField tf : indexedFieldList) {
            toCheck[indexedFieldList.indexOf(tf)] = Double.parseDouble(tf.getText());
        }
        double[] checked = checker.checkValidParameters(mySimType, toCheck);
        int index = 0;
        for (String validParam : myParams.keySet()) {
            myParams.put(validParam, checked[index]);
            index++;
        }
        this.close();
    }

    private void cancel() {
        this.close();
    }
}
