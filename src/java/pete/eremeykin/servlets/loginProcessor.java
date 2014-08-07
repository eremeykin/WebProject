/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.servlets;

import com.sun.media.sound.InvalidDataException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pete.eremeykin.common.DataBaseConnector;
import pete.eremeykin.common.Utils;

/**
 *
 * @author Pete
 */
@WebServlet(urlPatterns = {"/loginProcessor"})
public class loginProcessor extends HttpServlet {

    private static final DataBaseConnector baseConnector = new DataBaseConnector();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("Submit") != null) {
            processSubmit(request, response);
        }
        if (request.getParameter("Register") != null) {
            processRegister(request, response);
        }
    }

    protected void processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (baseConnector.checkPassword(request.getParameter("Login"), request.getParameter("Password"))) {
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("Login", Utils.specCharsConverter(request.getParameter("Login")));
                response.sendRedirect("mainpage.jsp");
            } else {
                Utils.sendMessage("", "Check login/password and try again", "index.jsp", response, request);
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Utils.send404(response, request);
        }
    }

    protected void processRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Utils.checkLoginFormData(request.getParameter("Login"), request.getParameter("Password"))) {
            try {
                if (request.getParameter("Login").contains("/") || request.getParameter("Login").contains("\\")) {
                    throw new InvalidDataException("");
                }
                baseConnector.insertInfo(request.getParameter("Login"), request.getParameter("Password"));
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("Login", Utils.specCharsConverter(request.getParameter("Login")));
                response.sendRedirect("mainpage.jsp");
            } catch (SQLException ex) {
                if (ex.getMessage().contains("ORA-00001:")) {
                    Utils.sendMessage("", "This user already exists", "index.jsp", response, request);
                } else {
                    Utils.sendError("data base connection error", ex.getMessage(), response, request);
                }
            } catch (NoSuchAlgorithmException ex) {
                Utils.sendError("security error", null, response, request);
            } catch (InvalidDataException e) {
                Utils.sendMessage("", "Please, enter your login without special characters like \"\\\" or \"/\"", "", response, request);
            }
        } else {
            Utils.sendMessage("", "Check form", "index.jsp", response, request);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
