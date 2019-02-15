package Configuration;

import Simulation.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.List;


/**
 * This codes purpose is to save the state of the current simulation to an XML file to be loaded at a later date.
 * I believe this code is well designed because it takes the burden off of other classes for providing the information,
 * and instead just requires a SimulationInfo object and the current Grid configuration. I refactored a lot of the code,
 * bringing it down from about 200 lines to about 150 by implementing a map to hold all of the values, and instead
 * of creating a new node manually each time I created a method to add the new node element.
 */



/**
 * Create new XML file and write current state to it
 * Creates all of the valid XML fields and puts the values from SimulationInfo object in them
 */
public class XMLWriter {
    private Document myDocument;
    private Element myRoot;
    private HashMap<String, String> myMap;
    private String[] myFields;
    private String[] myValues;

    /**
     * Save the new simulation using the string parameters as fields
     * @param title is the title of the simulation
     * @param simType is the type of simulation
     * @param configuration is the current state of the grid
     * @param width is the number of columns in the grid
     * @param height is the number of rows in the grid
     * @param shape is the shape of the objects in the grid
     * @param parameters is the state of the parameters of the grid
     * @param probabilities is the probabilities affecting randomness in the grid
     * @param neighborhood is the neighborhood of cells that get considered in calculating the next state
     * @param edge is the edge of the grid that must be considered
     * @param cellSize is the size of each cell
     * @param outline is true or false, describing whether or not the cells outline should appear
     * @param stateColors is a list of Strings representing the colors for each of the states
     */

    public XMLWriter(String title, String simType, String configuration, String width, String height, String shape,
                     String parameters, String probabilities, String neighborhood, String edge, String cellSize,
                     String outline, String stateColors){
        myFields = new String[]{"Title", "SimulationType", "GridConfiguration", "GridWidth", "GridHeight", "Shape",
        "SimParameters", "RandomProbabilities", "Neighborhood", "Edge", "GridSize", "OutlineFlag", "StateColors"};
        myValues = new String[]{title, simType, configuration, width, height, shape, parameters, probabilities, neighborhood,
        edge, cellSize, outline, stateColors};
        myMap = new HashMap<>();
        for(int k = 0; k < myValues.length; k++){
            myMap.put(myFields[k], myValues[k]);
        }
    }

    /**
     * Method that calls the constructor when all the information is taken in from a SimulationInfo object
     */

    public XMLWriter(List<String> values){
        this(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6),
                values.get(7), values.get(8), values.get(9), values.get(10), values.get(11), values.get(12));
    }

    /**
     * Write the XML file to a new save location. The grid is converted from the array to a string
     * and passed into the GridConfiguration so random is no longer enabled.
     * @param grid is the current grid with the states to be saved
     * @param saveLocation is the path where the XML will be saved
     */

    public void writeXML(Grid grid, File saveLocation){
        try{
            String XMLPath = saveLocation.getPath();
            int[][] gridInt = grid.getIntArray(grid.getHeight(), grid.getWidth());

            String integerConfiguration = arrayToString(gridInt);
            myMap.put("GridConfiguration", integerConfiguration);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();
            myDocument = document;

            myRoot = document.createElement("data");
            document.appendChild(myRoot);

            Attr attribute = document.createAttribute("media");
            attribute.setValue("Simulation");
            myRoot.setAttributeNode(attribute);

            for(String s:myMap.keySet()){
                myRoot.appendChild(myDocument.createTextNode("    "));
                createChildElement(s, myMap.get(s));
                myRoot.appendChild(myDocument.createTextNode("\n"));
            }

            TransformerFactory transformFactory = TransformerFactory.newInstance();
            Transformer transformer = transformFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult((new File(XMLPath)));
            transformer.transform(domSource, streamResult);
        }
        catch(ParserConfigurationException e){
            System.out.println("Error in parsing, configuration save failed");
        }
        catch(TransformerException e){
            System.out.println("Error in writing, configuration save failed");
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error, array size must be same as initial configuration file.");
        }
    }

    private void createChildElement(String elementName, String stringValue){
        Element newElement = myDocument.createElement(elementName);
        newElement.appendChild(myDocument.createTextNode(stringValue));
        myRoot.appendChild(newElement);
    }

    private String arrayToString(int[][] gridArray){
        String arrayString = "";
        for(int k = 0; k < gridArray.length; k++){
            for(int i = 0; i < gridArray[0].length; i++){
                arrayString = arrayString + gridArray[k][i] + " ";
            }
        }
        return arrayString;
    }
}