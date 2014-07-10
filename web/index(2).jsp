<%-- 
    Document   : index
    Created on : 08.07.2014, 20:30:16
    Author     : Pete
--%>

<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.Reader"%>
<%@page import="java.util.Locale"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.*"%>
<%@page import="pete.eremeykin.common.HTMLHelper"%>
<%@page import="pete.eremeykin.common.DataBaseConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <div align = "center">
            <form action="index.jsp" method="POST">
                <table border="0">
                    <thead>
                        <tr>
                            <th colspan="2">Please enter your login and password.</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Login:</td>
                            <td>
                                <input type="text" name="Login" value="" size="60" />
                            </td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td>
                                <input type="text" name="Password" value="" size="60" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"> 
                    <center>
                        <input type="submit" value="Login" name="Submit" />
                        <input type="submit" value="Register" name="Register" />
                    </center>                           
                    </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <% if (request.getParameter("Login") != null ) {
//            if (request.getParameter("Login") != null && !request.getParameter("query").trim().equals("")) {%>
        <%
            Connection connection = DataBaseConnector.getConnection();
            DataBaseConnector.insertInfo(connection, request.getParameter("Login"), request.getParameter("Password"));
        %>
        <%}%>
        <%= request.getParameter("Submit")%>
        
    </body>
</html>
