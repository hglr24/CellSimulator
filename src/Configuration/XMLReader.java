package Configuration;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class that gets all of the elements of the xml and the values stored in those elements to be passed to the
 * simulation info class
 */
public class XMLReader {
    private final DocumentBuilder myDocumentBuilder;

    /**
     * Constructor for the XMLReader that creates the private instance myDocumentBuilder
     */
    public XMLReader(){
        myDocumentBuilder = getDocumentBuilder();
    }

    /**
     * Provides the SimulationInfo created from the information in the datafile passed
     * @param dataFile XML file that you want to read the simulation information from
     * @return SimulationInfo object that contains all of the information for the initial configuration of the Simulation
     */
    public SimulationInfo getSimulation(File dataFile){
        var root = getRootElement(dataFile);
        var allFields = new HashMap<String, String>();
        for(var field: SimulationInfo.dataFields){
            allFields.put(field, getTextValue(root, field));
        }
        return new SimulationInfo(allFields);
    }

    private Element getRootElement(File xmlFile){
        try{
            myDocumentBuilder.reset();
            var xmlDocument = myDocumentBuilder.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch(SAXException | IOException e){
            throw new XMLException(e);
        }
    }

    private String getTextValue(Element e, String fieldName){
        var nodeList = e.getElementsByTagName(fieldName);
        if(nodeList != null && nodeList.getLength() > 0){
            return nodeList.item(0).getTextContent();
        }
        else{
            return "";
        }
    }

    private DocumentBuilder getDocumentBuilder(){
        try{
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e){
            throw new XMLException(e);
        }
    }
}
