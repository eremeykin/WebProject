/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import pete.eremeykin.common.AnsysQueryPerformer;
import pete.eremeykin.common.Utils;

/**
 *
 * @author Pete
 */
@WebServlet(name = "runQuery", urlPatterns = {"/runQuery"})
public class runQuery extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("Query") != null) {
            try {
                AnsysQueryPerformer aqp = new AnsysQueryPerformer();
                String workingDir = Utils.getSetting("Working_dir", "path");
                String userDirName = request.getSession().getAttribute("Login").toString();
                String projectName=request.getParameter("ProjectName");
                String dirName = workingDir + "\\" + userDirName + "\\" + projectName;
//                aqp.queryToFile(request.getParameter("Query"), userDirName, projectName);
                aqp.runQuery(request.getParameter("Query"), userDirName, projectName);
                response.sendRedirect("mainpage.jsp");
//                    out.print("Performed" + aqp.runQuery(null));
//                    out.print(aqp.queryToFile(request.getParameter("Query"), request.getSession().getAttribute("Login").toString()));
//                    out.print(aqp.printDirTree("E:\\"));
//                    response.sendRedirect("splitter.jsp");
//                    Utils.sendMessage(aqp.printDirTree("E:\\Книги&Журналы\\asm"), null, "mainpage.jsp", response, request);
//                    Utils.sendFile("C:/Users/Пётр/Downloads/sap-tatneft.pdf", response, request);

            } catch (ParserConfigurationException | SAXException ex) {
                Utils.sendError("configuration error", "An error occurred while reading the configuration file", response, request);
            } catch (FileNotFoundException ex) {
                Utils.sendError("configuration not found ", "the configuration file is not found, so site is not working. Please, try again later", response, request);
            } catch (InterruptedException ex) {
                Logger.getLogger(runQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
