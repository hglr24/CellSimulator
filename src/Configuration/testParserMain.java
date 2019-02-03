package Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class testParserMain{
    public static void main(String [] args){
        XMLReader testRead = new XMLReader();
        File dataFile = new File("data\\TestSegregation.xml");

        SimulationInfo testSim = testRead.getSimulation(dataFile);
        int[][] configArray = testSim.getIntegerConfiguration();
        double sum = 0;
        for(int k = 0; k < configArray.length; k++) {
            for(int j = 0; j < configArray[0].length; j++){
                sum = sum + configArray[k][j];
            }
        }
        double percent = sum/(testSim.getHeight()*testSim.getWidth());
        System.out.println(percent);
        System.out.println(testSim.getTitle().trim());
        double[] testParams = testSim.getParameters();
        System.out.println(testParams[0]);
    }
}
