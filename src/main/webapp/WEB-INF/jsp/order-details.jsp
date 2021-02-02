<%-- 
    Document   : order-details
    Created on : 20 січ. 2021, 19:28:26
    Author     : dmytr
--%>

<%@page import="com.mycompany.electronicstore.model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order-details</title>
        <link rel="stylesheet" href="/css/index.css" type="text/css">
        <link rel="stylesheet" href="/css/orders.css" type="text/css"/>
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
        <table>
            <tr>
                <td>Id:</td>
                <td>${order.getId()}</td>
            </tr>
            <tr>
                <td>Ordered by:</td>
                <td>${order.getUser().getFirstName()} ${order.getUser().getLastName()}</td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>${order.getUser().getEmail()}</td>
            </tr>
            <tr>
                <td>Executed:</td>
                <td>${order.isExecuted()}</td>
            </tr>
            <tr>
                <td>Ordered items:</td>
                <td>
                <c:forEach items="${order.getCommodities()}" var="comm">
                    <div id="basketItem" class="basketItem">
                        ${comm.addImage()}
                        <p>${comm.getBrand().getName()} ${comm.getModel()}</p>
                        <p id="addedItemPrice">${comm.getPrice()}</p>
                    </div>
                </c:forEach>
                </td>
            </tr>
            <tr>
                <td>Total sum:</td>
                <td>${order.getCommodities().stream().map(c->c.getPrice()).reduce(0, (acc,x)->acc+x)}</td>
            </tr>
        </table>
            <%
                Order order=(Order)request.getAttribute("order");
                if(!order.isExecuted()){
                    out.println(
                        "<form action=\"/orders/execute/"+order.getId()+"\" method=\"POST\">"+
                        "<input type=\"submit\" id=\"execute\" value=\"Mark as executed\"/>"+
                        "</form>"
                    );
                }
            %>            
    </body>
</html>
