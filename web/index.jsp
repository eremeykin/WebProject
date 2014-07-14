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
<html lang="en">
    <head>
        <title></title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="res/css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="res/css/grid.css" type="text/css" media="all">
        <link rel="stylesheet" href="res/css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="res/css/jquery-ui-1.8.5.custom.css" type="text/css" media="all">
        <script type="text/javascript" src="res/js/jquery-1.4.2.min.js" ></script>
        <script type="text/javascript" src="res/js/jquery.cycle.all.js"></script>
        <script type="text/javascript" src="res/js/jquery-ui-1.8.5.custom.min.js"></script>
        <!--[if lt IE 9]>
                <script type="text/javascript" src="js/html5.js"></script>
        <![endif]-->
    </head>
    <%
        if (request.getParameter("param") != null && request.getParameter("param").equals("logout")) {
            request.getSession().removeAttribute("isActive");
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        } else {
            HttpSession httpSession = request.getSession(true);
            String name = (String) httpSession.getAttribute("Login");
            if (name != null) {
                response.sendRedirect("mainpage.jsp");
            }
        }
    %>
    <body>
        <header>
            <nav>
                <div class="container">
                    <div class="wrapper">
                        <h1><a href="index.jsp"><strong>Ansys</strong>Online</a></h1>
                    </div>
                </div>
            </nav>
        </header>
        <section id="content">
            <div class="top">
                <div class="container">
                    <div class="clearfix" align="center">
                        <section id="intro">
                            <form action="loginProcessor" method="POST" id="login-form">
                                <table height="300" border="2">
                                    <thead>
                                    <th colspan="3"><h2>Login/Register</h2><br>Please enter your login and password.</th>
                                    </thead>
                                    <tbody>
                                        <tr height="30">
                                            <td>
                                                Login:
                                            </td>
                                            <td>
                                                <input type="email"  id="email" name="Login" value="" size="60" />
                                            </td>
                                        </tr>
                                        <tr height="30">
                                            <td>
                                                Password:
                                            </td>
                                            <td>
                                                <input type="password" name="Password" value="" size="60" />
                                            </td>
                                        </tr>
                                        <tr height="30">
                                            <td colspan="2" align="center"> 
                                                <input type="submit" value="Login" name="Submit" />
                                                <input type="submit" value="Register" name="Register" />
                                            </td>
                                        </tr>
                                        <tr height="110">
                                            <td align="center" colspan="3" style="vertical-align: top; color: #f44">
                                                <%=(String) request.getAttribute("ErrorMsg") != null ? (String) request.getAttribute("ErrorMsg") : ""%>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </section>
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <div class="container">
                <div class="wrapper">
                    <div class="copy">Eremeykin Pete (c) 2014	|	<a href="">Privacy policy</a></div>
                    <address class="phone">
                        We're glad to help you. Please email us. <strong>eremeykin@gmail.com</strong>
                    </address>
                </div>
            </div>
        </footer>
    </body>
</html>