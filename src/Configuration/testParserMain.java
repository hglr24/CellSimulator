package Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class testParserMain{
    public static void main(String [] args){
        XMLReader testRead = new XMLReader("SimulationInfo");
        File dataFile = new File("data\\TestXML.xml");

        SimulationInfo testSim = testRead.getSimulation(dataFile);
        int[][] configArray = testSim.getConfiguration();

    }
}
