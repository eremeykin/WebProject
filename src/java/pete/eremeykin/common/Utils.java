/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.common;

import java.io.*;
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

    public static void sendMessage(String header, String message, String URL, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html");
        request.setAttribute("msg", message != null ? message : "");
        request.setAttribute("header", header != null ? header : "");
        try {
            request.getRequestDispatcher(URL).forward(request, response);
        } catch (ServletException | IOException ex) {
//            sendError("error while sending message", ex.getMessage(), response, request);
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

    public static String specCharsConverter(String str) {
        str = str.replace("&", "&amp");
        str = str.replace(">", "&gt");
        str = str.replace("<", "&lt");
        str = str.replace("\"", "&quot");
        return str;
    }

    //source: http://www.codenet.ru/webmast/java/jspupl.php
    public static void sendFile(String fileName, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Content-Type", "application/octet-stream;");
        String shortName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
        response.setHeader("Content-Disposition", "filename=\"" + shortName + "\"");
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));
            BufferedOutputStream binout = new BufferedOutputStream(response.getOutputStream());
            int ch = in.read();
            while (ch != -1) {
                binout.write(ch);
                ch = in.read();
            }
            binout.close();
            in.close();
        } catch (IOException ioe) {
            sendError("error while sending file", "An error occurred while trying to download file:"+shortName, response, request);
        }
    }

    //temp
    public static String printDirTree(String startDir) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
        File dir = new File(startDir);
        dir.mkdirs();
        String[] content = dir.list();
        String res = "<div class=\"box\">\n"
                + "<h4><span><nobr>"
                + "<img src=\"res/images/folder.png\" height=\"15\" width=\"15\">&nbsp"
                + dir.getName()
                + "</nobr></span></h4>\n"
                + "<ul style=\"margin-left:15px\">";
        for (String d : dir.list()) {
            File currFile = new File(startDir + "\\" + d);
            if (currFile.isDirectory()) {

                res = res + printDirTree(currFile.getAbsolutePath());
            }
        }
        for (String d : dir.list()) {
            File currFile = new File(startDir + "\\" + d);
            if (!currFile.isDirectory()) {
                int beginIndex = ConfigLoader.WORKING_DIR.length();
                String requestedFilePath = currFile.getAbsolutePath();
                requestedFilePath = requestedFilePath.substring(beginIndex);
                res = res
                        + "<li>"
                        + "<a href=\"downloadFile?file=" + requestedFilePath + "\"\">"
                        + d
                        + "</a>"
                        + "</li>"
                        + "\n";
            }
        }

        res = res + "</ul>\n"
                + "</div>";
        return res;
    }
}
