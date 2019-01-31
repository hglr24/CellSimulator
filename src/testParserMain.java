import javafx.application.Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class testParserMain{
    public static void main(String [] args){
        XMLReader testRead = new XMLReader("SimulationInfo");
        File dataFile = new File("data\\TestXML.xml");

        BufferedReader br = new BufferedReader(new FileReader(dataFile));
//        String outString = br.readLine();
//        System.out.println(outString);
//        SimulationInfo testSim = testRead.getSimulation(dataFile);
//        System.out.println(examplePath);
    }

}
