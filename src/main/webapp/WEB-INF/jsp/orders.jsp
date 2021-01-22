<%-- 
    Document   : orders
    Created on : 20 січ. 2021, 18:51:45
    Author     : dmytr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>
        <link rel="stylesheet" href="/css/index.css" type="text/css"/>
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
                  <th>Id</th>
                  <th>Fullname</th>
                  <th colspan="2">Executed</th>                  
              </tr>
              <c:forEach items="${orders}" var="order">
                  <tr>
                      <td>${order.getId()}</td>
                      <td><a href="/orders/read/${order.getId()}">
                              ${order.getUser().getFirstName()} ${order.getUser().getLastName()} 
                          </a>
                      </td>
                      <td>${order.isExecuted()}</td>
                      <td><a href="/orders/delete/${order.getId()}">Delete</a></td>
                  </tr>
              </c:forEach>
          </table>
    </body>
</html>
