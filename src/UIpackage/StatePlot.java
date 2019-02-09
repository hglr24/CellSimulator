package UIpackage;

import Simulation.BasicGrid;
import Simulation.State;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import java.util.HashMap;

class StatePlot extends HBox {
    private LineChart myChart;
    private HashMap<State, Integer> myDataMap;
    private HashMap<State, XYChart.Series<Number, Number>> mySeriesMap;
    private BasicGrid myGrid;
    private int myFrameCount;

    StatePlot(BasicGrid grid) {
        myGrid = grid;
        myDataMap = myGrid.getCounts();
        mySeriesMap = new HashMap<>();
        myFrameCount = 0;
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        myChart = new LineChart<>(xAxis, yAxis);
        myChart.getStyleClass().add("right-pane-item");
        myChart.setCreateSymbols(false);
        setProperties();
        makeSeriesMap();
    }

    private void setProperties() {
        this.getChildren().add(myChart);
        myChart.setTitle("State Chart");
    }

    private void makeSeriesMap() {
        for (State s : myDataMap.keySet()) {
            XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
            mySeriesMap.put(s, newSeries);
            myChart.getData().add(newSeries);
            String colorString = s.getColor().toString();
            colorString = colorString.replace("0x", "");
            newSeries.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #" + colorString + ";");
        }
    }

    void update(BasicGrid grid) {
        myGrid = grid;
        myDataMap = myGrid.getCounts();
        for (State s : myDataMap.keySet()) {
            mySeriesMap.get(s).getData().add(new XYChart.Data<>(myFrameCount, myDataMap.get(s)));
        }
        myFrameCount++;
    }
}