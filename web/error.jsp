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
                            <table border="2" height="300" width="500">
                                <thead>
                                    <tr>
                                        <th>
                                <h2 style="color:#ff4444">Error</h2>
                                <h2><strong>Sorry, </strong><%=(String) request.getAttribute("header")%><br></h2>
                                    <%=(String) request.getAttribute("msg")%>
                                </th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>

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