/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                Utils.sendError("success", "", response, request);
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
                baseConnector.insertInfo(request.getParameter("Login"), request.getParameter("Password"));
                Utils.sendError("success", null, response, request);
            } catch (SQLException ex) {
                if (ex.getMessage().contains("ORA-00001:")) {
                    Utils.sendMessage("", "This user already exists", "index.jsp", response, request);
                } else {
                    Utils.sendError("data base connection error", ex.getMessage(), response, request);
                }
            } catch (NoSuchAlgorithmException ex) {
                Utils.sendError("security error", null, response, request);
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
