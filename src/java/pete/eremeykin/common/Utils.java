/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import pete.eremeykin.servlets.loginProcessor;

/**
 *
 * @author Pete
 */
public class Utils {

    public static String getSHA(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static void sendMessage(String errHeader, String errMessage, String URL, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html");
        request.setAttribute("ErrorMsg", errMessage != null ? errMessage : "");
        request.setAttribute("ErrorHeader", errHeader != null ? errHeader : "");
        try {
            request.getRequestDispatcher(URL).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(loginProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendError(String errHeader, String errMessage, HttpServletResponse response, HttpServletRequest request) {
        sendMessage(errHeader, errMessage, "error.jsp", response, request);
    }

    public static void send404(HttpServletResponse response, HttpServletRequest request) {
        sendError("page not found", "Sorry, but the page you are looking for has not been found."
                + "Try checking the URL for errors, then hit the refresh button on your browser.", response, request);
    }

    public static boolean checkLoginFormData(String login, String password) {
        return login != null && password != null && !login.equals("") && !password.equals("");
    }

    private static Document getDoc() throws SAXException, IOException, ParserConfigurationException {
        Document doc;
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = f.newDocumentBuilder();
        File config = new File("C:\\Users\\Пётр\\Documents\\NetBeansProjects\\AnsysProject\\src\\java\\config\\config.xml");
        doc = builder.parse(config);
        return doc;
    }

    public static String getSetting(String tagName, String attrName) throws SAXException, IOException, ParserConfigurationException {
        Document doc = getDoc();
        NodeList nl = doc.getElementsByTagName(tagName);
        Element el = (Element) nl.item(0);
        return el.getAttribute(attrName);
    }
}
