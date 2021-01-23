<%-- 
    Document   : registration
    Created on : 22 січ. 2021, 21:30:41
    Author     : dmytr
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/css/index.css" type="text/css">
        <title>Registration</title>
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
        <form:form action="/users/create" method="POST" modelAttribute="user">
            <table>
                <tr>
                    <td>Firstname:</td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td>Lastname:</td>
                    <td><form:input path="lastName"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><form:input path="email"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:input type="password" path="password"/></td>
                </tr>
            </table>
            <input type="submit" value="register"/>
        </form:form>
    </body>
</html>
