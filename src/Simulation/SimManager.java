package Simulation;

import Configuration.SimulationInfo;
import Configuration.XMLReader;

import java.io.File;

public class SimManager {
    public static void main(){
        XMLReader testRead = new XMLReader();
        File dataFile = new File("data\\TestSegregation.xml");
        SimulationInfo testSim = testRead.getSimulation(dataFile);





    }
}
