<%@page import="pete.eremeykin.common.Utils"%>
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

        <link rel="stylesheet" type="text/css" href="res/css/splitter.css" />
        <style type="text/css" media="all">

            body {
                padding: 0px;
            }

            /*
             * Splitter container. Set this to the desired width and height
             * of the combined left and right panes. In this example, the
             * height is fixed and the width is the full width of the body,
             * less the margin on the splitter itself.
             */
            #MySplitter {
                height: 400px;
                margin: 1em 3em;
                /*border: 4px solid #bdb;*/
                /* No padding allowed */
            }
            /*
             * Left-side element of the splitter. Use pixel units for the
             * min-width and max-width; the splitter plugin parses them to
             * determine the splitter movement limits. Set the width to
             * the desired initial width of the element; the plugin changes
             * the width of this element dynamically.
             */
            #LeftPane {
                /*background: #efe;*/
                overflow: auto;
                /*padding: 3px;*/
                /* No margin or border allowed */
            }
            /*
             * Right-side element of the splitter.
             */
            #RightPane {
                /*background: #f8fff8;*/
                overflow: auto;
                /*padding: 3px;*/
                /* No margin or border allowed */
            }
            /* 
             * Splitter bar style; the .active class is added when the
             * mouse is over the splitter or the splitter is focused
             * via the keyboard taborder or an accessKey. 
             */
            #MySplitter .vsplitbar {
                width: 6px;
                background: #aca url(img/vgrabber.gif) no-repeat center;
            }
            #MySplitter .vsplitbar.active {
                background: #da8 url(img/vgrabber.gif) no-repeat center;
                opacity: 0.7;
            }
        </style>
        <script type="text/javascript">

            $().ready(function() {
                $("#MySplitter").splitter({
                    type: "v",
                    outline: true,
                    minLeft: 100, sizeLeft: 150, minRight: 100,
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
                            <div id="MySplitter">
                                <div id="LeftPane">
                                    
                                    <%=Utils.printDirTree("C:\\masm32")%>
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
                                        <textarea id="text-area" name="Query">Here should be your APDL query... </textarea>
                                        <!--<input type="submit" name="run" value="Run" size="60" style="float: right; padding-right: 20px"/>-->
                                        <input type="submit" name="run" value="Run" size="60"/>
                                    </form>
                                </div>
                            </div>
<!--                            <table border="0">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>

                                        </td>
                                        <td>
                                            some content
                                        </td>
                                    </tr>
                                </tbody>
                            </table>-->
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