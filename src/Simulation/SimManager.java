package Simulation;

import Configuration.SimulationInfo;
import Configuration.XMLReader;

import java.io.File;

public class SimManager {
    public static void main(){
        XMLReader testRead = new XMLReader("SimulationInfo");
        File dataFile = new File("data\\TestSegregation.xml");
        SimulationInfo testSim = testRead.getSimulation(dataFile);

        switch(testSim.getTitle()){
            default:
            SegregationRuleSet rules = new SegregationRuleSet(testSim.getParameters());
            SegregationGrid sg = new SegregationGrid(testSim.getHeight(),testSim.getWidth(),
                    testSim.getIntegerConfiguration(),rules);
    }


    }
}
