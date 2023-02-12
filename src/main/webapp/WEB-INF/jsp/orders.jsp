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
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    </head>
    <body>        
        <div id="headerContainer">
            <div id="titleAndButtonContainer">
                <h1 id="title">
                    <a href="/index" id="homeLink"><img src="/images/favicon.png" alt="Electronic Webstore">Electronic Webstore</a>
                </h1><br>
                <button id="basket" class="fas fa-shopping-cart">Basket</button>
            </div>
        </div>
        <form action="/orders/filter" id="ordersForm" method="POST">
            <label>All
            <input type="radio" name="isExecuted" value="all" checked/>
            </label>
            <label>Executed
            <input type="radio" name="isExecuted" value="true"/>
            </label>
            <label>Unexecuted
            <input type="radio" name="isExecuted" value="false"/>
            </label>
            <input type="submit" id="submitOrderFilterCriteria" value="Submit"/>
        </form>
        <table>
              <tr>
                  <th>Id</th>
                  <th>Fullname</th>
                  <th colspan="2">Executed</th>                  
              </tr>              
              <c:forEach items="${orderMap}" var="orderPair">
                  <tr>
                      <td>${orderPair.getKey().getId()}</td>
                      <td><a href="/orders/read/${orderPair.getKey().getId()}">
                              ${orderPair.getValue()} 
                          </a>
                      </td>
                      <td>${orderPair.getKey().isExecuted()}</td>
                      <td><a href="/orders/delete/${orderPair.getKey().getId()}">Delete</a></td>
                  </tr>
              </c:forEach>
          </table>
    </body>
</html>
