<%-- 
    Document   : error
    Created on : 25 лист. 2020, 15:03:01
    Author     : dmytr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error page</title>
        <link rel="stylesheet" href="/css/index.css" type="text/css"> 
    </head>
    <body>        
        <div id="headerContainer">
            <div id="titleAndButtonContainer">
                <h1 id="title">
                    <a href="/index" id="homeLink"><img src="/images/favicon.png" alt="Electronic Webstore">Electronic Webstore</a>
                </h1><br>
                <input type="button" id="basket" value="Basket">
            </div>
        </div>
        <h1 style="margin: 0 auto; width: max-content">
            ${code}
            <%
                if(request.getAttribute("code")==null){
                    out.print("404 Not Found");
                }
            %>
        </h1>
        <h2 style="margin: 0 auto; width: max-content">
            ${message}
            <%
                if(request.getAttribute("message")==null || request.getAttribute("message")==""){
                    out.print("Page does not exist");
                }
            %>
        </h2>        
    </body>
</html>
