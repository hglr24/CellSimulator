

import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XMLReader {
    public static final String errorMessage = "Error while parsing XML";
    private final DocumentBuilder myDocumentBuilder;
    private final String myType;

    public XMLReader(String type){
        myDocumentBuilder = getDocumentBuilder();
        myType = type;
    }

    public Simulation getSimulation(File dataFile){
        var root = getRootElement(dataFile);
        if(! isValidFile(root, Simulation.type)){
            throw new XMLException(errorMessage, Simulation.type);
        }
        var allFields = new HashMap<String, String>;
        for(var field:Simulation.dataFields){
            allFields.put(field, getTextValue(root, field));
        }
        return new Simulation(allFields);
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

    private boolean isValidFile(Element root, String type){
        return getAttribute(root, myType).equals(type);
    }

    private String getAttribute(Element e, String attributeName){
        return e.getAttribute(attributeName);
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
