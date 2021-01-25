<%-- 
    Document   : shopping-cart
    Created on : 23 січ. 2021, 18:55:38
    Author     : dmytr
--%>

<%@page import="com.mycompany.electronicstore.model.Commodity"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="popup_window">
    <input type="button" id="close" value="&times;"><br>           
    <div id="addedItems">
        <%
            List<Commodity> basket=(List<Commodity>)request.getAttribute("basket");
            if(basket.size()==0){out.println("<div class=\"emptyBasket\">Basket is empty</div>");}
            if(request.getParameter("shouldBasketBeOpened")!=null){
                out.println("<div class=\"shouldBasketBeOpened\" hidden></div>");
            }
        %>
        <c:forEach items="${basket}" var="basketItem">
            <div id="basketItem">
                ${basketItem.addImage()}
                <p>${basketItem.getBrand().getName()} ${basketItem.getModel()}</p>
                <p id="addedItemPrice">${basketItem.getPrice()}</p>
                <form action="/orders/remove/${basket.indexOf(basketItem)}" method="POST">
                    <input type="submit" class="remove" value="Remove"/>
                </form>
            </div>
        </c:forEach>
        <p id="totalSum">Total sum:${basket.stream().map(c->c.getPrice()).sum()}</p>
    </div>
    <sec:authorize access="!isAuthenticated()">
        <p>To make an order <span id="loginToOrder">login</span> or <a href="/users/create">register</a></p>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div class="buyingProposal">To make an order add something into basket, please</div>
        <form action="/orders/create" method="POST" class="orderingForm">        
            <input type="submit" id="send" value="Make an order">
        </form>
    </sec:authorize>
</div>
