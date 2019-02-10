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
import java.util.List;

public class XMLWriter {
    private String myTitle;
    private String mySimType;
    private String myConfiguration;
    private String myWidth;
    private String myHeight;
    private String myShape;
    private String myParameters;
    private String myProbabilities;
    private String myNeighborhood;
    private String myEdge;
    private String myCellSize;
    private String myOutline;
    private String myStateColors;

    public XMLWriter(String title, String simType, String configuration, String width, String height, String shape,
                     String parameters, String probabilities, String neighborhood, String edge, String cellSize,
                     String outline, String stateColors){
        myTitle = title;
        mySimType = simType;
        myConfiguration = configuration;
        myWidth = width;
        myHeight = height;
        myShape = shape;
        myParameters = parameters;
        myProbabilities = probabilities;
        myNeighborhood = neighborhood;
        myEdge = edge;
        myCellSize = cellSize;
        myOutline = outline;
        myStateColors = stateColors;
    }

    public XMLWriter(List<String> values){
        this(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6),
                values.get(7), values.get(8), values.get(9), values.get(10), values.get(11), values.get(12));
    }

    public void writeXML(Grid grid, File saveLocation){
        try{
            String XMLPath = saveLocation.getPath();
            int[][] gridInt = grid.getIntArray(grid.getHeight(), grid.getWidth());
            myConfiguration = arrayToString(gridInt);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Element root = document.createElement("data");
            document.appendChild(root);
            Attr attribute = document.createAttribute("media");
            attribute.setValue("Simulation");
            root.setAttributeNode(attribute);

            Element title = document.createElement("Title");
            title.appendChild(document.createTextNode(myTitle));
            root.appendChild(title);

            Element simType = document.createElement("SimulationType");
            simType.appendChild(document.createTextNode(mySimType));
            root.appendChild(simType);

            Element gridConfig = document.createElement("GridConfiguration");
            gridConfig.appendChild(document.createTextNode(myConfiguration));
            root.appendChild(gridConfig);

            Element gridWidth = document.createElement("GridWidth");
            gridWidth.appendChild(document.createTextNode(myWidth));
            root.appendChild(gridWidth);

            Element gridHeight = document.createElement("GridHeight");
            gridHeight.appendChild(document.createTextNode(myHeight));
            root.appendChild(gridHeight);

            Element shape = document.createElement("Shape");
            shape.appendChild(document.createTextNode(myShape));
            root.appendChild(shape);

            Element simParams =document.createElement("SimParameters");
            simParams.appendChild(document.createTextNode(myParameters));
            root.appendChild(simParams);

            Element randProbs = document.createElement("RandomProbabilities");
            randProbs.appendChild(document.createTextNode(myProbabilities));
            root.appendChild(randProbs);

            Element neighborhood = document.createElement("Neighborhood");
            neighborhood.appendChild(document.createTextNode(myNeighborhood));
            root.appendChild(neighborhood);

            Element edge = document.createElement("Edge");
            edge.appendChild(document.createTextNode(myEdge));
            root.appendChild(edge);

            Element gridSize = document.createElement("GridSize");
            gridSize.appendChild(document.createTextNode(myCellSize));
            root.appendChild(gridSize);

            Element outlineFlag = document.createElement("OutlineFlag");
            outlineFlag.appendChild(document.createTextNode(myOutline));
            root.appendChild(outlineFlag);

            Element stateColors = document.createElement("StateColors");
            stateColors.appendChild(document.createTextNode(myStateColors));
            root.appendChild(stateColors);

            TransformerFactory transformFac = TransformerFactory.newInstance();
            Transformer transformer = transformFac.newTransformer();
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