/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.common;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public static void sendError(String errHeader, String errMessage, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html");
        request.setAttribute("ErrorMsg", errMessage);
        request.setAttribute("ErrorHeader", errHeader);
        try {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(loginProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void send404(HttpServletResponse response, HttpServletRequest request) {
        sendError("page not found", "Sorry, but the page you are looking for has not been found."
                + "Try checking the URL for errors, then hit the refresh button on your browser.", response, request);
    }
}
