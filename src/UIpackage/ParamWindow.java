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
import java.util.TreeMap;

/**
 * Creates a new parameter display window for given simulation, closes itself when user selects apply or close
 */
class ParamWindow extends Stage {

    private RuleSet myRules;
    private TreeMap<String, Double> myParams;
    private ArrayList<Double> myOldVals;
    private ArrayList<TextField> myFieldList = new ArrayList<>();
    private SimulationType mySimType;
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
        myParams = new TreeMap<>(myRules.getParameters());
        myOldVals = new ArrayList<>(myParams.values());
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
        myFieldList.add(paramField);
        paramGrid.add(paramLabel, 0, row);
        paramGrid.add(paramField, 1, row);
    }

    private void applyParams() {
        double[] paramsToCheck = new double[myParams.keySet().size()];
        double[] paramToRevert = new double[paramsToCheck.length];
        CheckParameters checker = new CheckParameters();
        for (int i = 0; i < paramToRevert.length; i++) {
            paramToRevert[i] = myOldVals.get(i);
            paramsToCheck[i] = Double.parseDouble(myFieldList.get(i).getText());
        }
        double[] checkedParams = checker.checkValidParameters(mySimType, paramsToCheck, paramToRevert);
        myRules.setParameters(checkedParams);
        this.close();
    }

    private void cancel() {
        this.close();
    }
}
