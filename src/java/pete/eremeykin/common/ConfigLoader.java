/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Pete
 */
public class ConfigLoader {

    static {
        try {
            ANSYS_DIR = getSetting("Ansys_dir", "path");
            WORKING_DIR = getSetting("Working_dir", "path");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            
            //!!! написать обработку
        }
    }

    public static String ANSYS_DIR;
    public static String WORKING_DIR;
    public static final String CONFIG_DIR="C:\\Users\\Пётр\\Documents\\NetBeansProjects\\AnsysProject\\src\\java\\config\\config.xml";

    private static String getSetting(String tagName, String attrName) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
        Document doc = getDoc();
        NodeList nl = doc.getElementsByTagName(tagName);
        Element el = (Element) nl.item(0);
        return el.getAttribute(attrName);
    }

    private static Document getDoc() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
        Document doc;
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = f.newDocumentBuilder();
        File config = new File(CONFIG_DIR);
        doc = builder.parse(config);
        return doc;
    }

}
