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
        <%
            HttpSession httpSession = request.getSession(true);
            String name = (String) httpSession.getAttribute("Login");
            if (name == null) {
                response.sendRedirect("index.jsp");
            }
        %>
    </head>
    <body>
        <header>
            <nav>
                <div class="container">
                    <div class="wrapper">
                        <h1><a href="index.jsp"><strong>Ansys</strong>Online</a></h1>
                        <!--<div style="float: right;">We are glad to see you!</div>-->
                        <h3 style="font-size: small">We are glad to see you, <%=request.getSession(true).getAttribute("Login")%>!
                            <a href="index.jsp?param=logout">                                
                                <img src="res/images/logout.png" width="15" height="15" alt="logout"></h3>
                            </a>
                    </div>
                </div>
            </nav>
        </header>
        <section id="content">
            <div class="middle">
                <div class="container">
                    <div class="wrapper">
                        <div class="grid3 first">
                            <ul class="categories">
                                <li><a href="#">New query</a></li>
                                <li><a href="">History</a></li>
                            </ul>
                        </div>
                        <div class="grid9">
                            <form action="runQuery" method="POST" id="login-form">
                                <textarea id="text-area" name="Query">Here should be your APDL query... </textarea>
                                <input type="submit" name="run" value="Run" size="60" style="float: right; padding-right: 20px"/>
                            </form>
                            </tbody>
                            </table>
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