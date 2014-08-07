<%@page errorPage="unknown_error.jsp" %>
<%@page import="java.io.*"%>
<%@page import="pete.eremeykin.common.Utils"%>
<%@page import="pete.eremeykin.common.ConfigLoader"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title></title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="res/css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="res/css/grid.css" type="text/css" media="all">
        <link rel="stylesheet" href="res/css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="res/css/jquery-ui-1.8.5.custom.css" type="text/css" media="all">
        <link rel="stylesheet" type="text/css" href="res/css/splitter.css" />
        <script type="text/javascript" src="res/js/jquery-1.4.2.min.js" ></script>
        <script type="text/javascript" src="res/js/jquery.cycle.all.js"></script>
        <script type="text/javascript" src="res/js/jquery-ui-1.8.5.custom.min.js"></script>
        <script type="text/javascript" src="res/js/jquery-splitter.js"></script>
        <script type="text/javascript" src="res/js/jquery.cookie.js"></script>
        <script type="text/javascript" src="res/js/splitter.js"></script>
        <!--[if lt IE 9]>
                <script type="text/javascript" src="js/html5.js"></script>
        <![endif]-->
        <%
            HttpSession httpSession = request.getSession(true);
            String name = (String) httpSession.getAttribute("Login");
            if (name == null) {
                response.sendRedirect("index.jsp");
            }
        %>

        <script type="text/javascript">

            $().ready(function() {
                $("#MySplitter").splitter({
                    type: "v",
                    outline: true,
                    minLeft: 125, sizeLeft: 150, minRight: 650,
                    resizeToWidth: true,
                    cookie: "vsplitter",
                    accessKey: 'I'
                });
            });

        </script>
    </head>
    <body>
        <header>
            <nav>
                <div class="container">
                    <div class="wrapper">
                        <h1>
                            <a href="index.jsp">
                                <img src="res/images/logo.png" width="8%" height="8%">
                                <strong>Ansys</strong>Online</a>
                        </h1>
                        <!--<div style="float: right;">We are glad to see you!</div>-->
                        <h3 style="font-size: small">We are glad to see you, <%=request.getSession(true).getAttribute("Login")%>!
                            <a href="index.jsp?param=logout">                                
                                <img src="res/images/logout.png" width="15" height="15" alt="logout">
                            </a>
                        </h3>
                    </div>
                </div>
            </nav>
        </header>
        <section id="content">
            <div class="middle" style="padding: 0px 0px 10px 0px;">
                <div class="container">
                    <div class="wrapper">
                        <div class="grid9" style="float: right;">
                            <div id="MySplitter">
                                <div id="LeftPane">
                                    <%
                                        try {
                                    %>

                                    <%=Utils.printDirTree(ConfigLoader.WORKING_DIR + "\\"
                                            + session.getAttribute("Login"))%>

                                    <%} catch (Exception e) {
                                            Utils.sendError("configuration not found ", "The configuration file is not found, so the site is not working. Please, try again later.", response, request);
                                        }
                                    %>

                                    <script>
                                        $(document).ready(function() {
                                            $("ul").hide();
                                            $("h4 span").click(function() {
                                                $(this).parent().next().slideToggle();
                                            });
                                        });
                                    </script>
                                </div>
                                <div id="RightPane">
                                    <form action="runQuery" method="POST" id="login-form">
                                        <!--<input type="text" name="QueryName" value="Query name">-->
                                        <input type="text" name="ProjectName" style="margin-left: 3px; margin-bottom: 3px; width: 98%-30px; " >
                                        <input type="submit" name="run" value="Run" size="60" style="margin-bottom: 3px"/>
                                        <!--<textarea id="text-area" name="ProjectName" style="height: 15px; width: 99%-50px;margin-left: 3px; resize: none; font-size: medium">Here should be your APDL query... </textarea>-->
                                        <textarea id="text-area" name="Query" style="height: 345px; width: 99%;margin-left: 3px; resize: none;">Here should be your APDL query... </textarea>
                                        <!--<input type="submit" name="run" value="Run" size="60" style="float: right; padding-right: 20px"/>-->
                                    </form>
                                </div>
                            </div>
                        </div>
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