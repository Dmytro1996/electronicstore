<%-- 
    Document   : shopping-cart
    Created on : 23 січ. 2021, 18:55:38
    Author     : dmytr
--%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="popup_window">
    <input type="button" id="close" value="&times;"><br>           
    <div id="addedItems" class="emptyBasket"></div>
    <sec:authorize access="!isAuthenticated()">
        <p>To make an order <span id="loginToOrder">login</span> or <a href="/users/create">register</a></p>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <form action="/orders/create" method="POST">        
            <input type="submit" id="send" value="Make an order">
        </form>
    </sec:authorize>
</div>
