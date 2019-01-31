package Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class testParserMain{
    public static void main(String [] args){
        XMLReader testRead = new XMLReader("Configuration.SimulationInfo");
        File dataFile = new File("data\\TestXML.xml");

//        String outString = br.readLine();
//        System.out.println(outString);
//        Configuration.SimulationInfo testSim = testRead.getSimulation(dataFile);
//        System.out.println(examplePath);
    }

}
