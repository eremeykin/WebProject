<%-- 
    Document   : FolderView_test
    Created on : 02.08.2014, 23:09:34
    Author     : Pete
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <script src="res/js/jquery-latest.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%=(String) request.getAttribute("header")%><br>
        <%=(String) request.getAttribute("msg")%>
        <h1>________________________________</h1>
        <style>
            h3 { cursor:pointer;}
        </style>

        <script>
            $(document).ready(function() {
                $("ul").hide();
                $("h3 span").click(function() {
                    $(this).parent().next().slideToggle();
                });
            });
        </script>
    </body>
</html>
