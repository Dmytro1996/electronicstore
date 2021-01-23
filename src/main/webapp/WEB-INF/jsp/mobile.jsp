<%-- 
    Document   : mobile
    Created on : 2 лип. 2020, 19:18:05
    Author     : dmytr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mobile phones</title>
        <link rel="stylesheet" href="/css/index.css" type="text/css">
        <link rel="stylesheet" href="/css/mobile.css" type="text/css"> 
        <link rel="stylesheet" href="/css/commodity.css" type="text/css">
        <style><%@include file="\css\index.css"%></style>
        <style><%@include file="\css\mobile.css"%></style>
        <style><%@include file="\css\commodity.css"%></style>
    </head>
    <body>
       <div id="headerContainer">
          <jsp:include page="login-form.jsp"/>
          <div id="titleAndButtonContainer">
            <h1 id="title">Mobile phones</h1>
            <input type="button" id="basket" value="Basket">
          </div>          
          <div id="otherPagesLinks">
              <a href="/tv/all">TVs</a>
              <a href="/mobile/all">Mobile phones</a>
              <a href="/laptop/all">Laptops</a>
              <a href="/acc/all">Accesories</a>
          </div>
        </div>        
        <div id="form">
          <form action="/mobile/filter" method="POST">
            <div id="priceInput">
              <label>Price from:</label>
              <label><span id="minPriceValue"></span></label><br>                
              <input type="range" name="minPrice" min="0" max="100000" value="0" class="slider" id="minPrice"><br>
              <label>To:<label id="maxPriceValue"></label></label><br>
              <input type="range" name="maxPrice" min="0" max="100000" value="100000" class="slider" id="maxPrice"><br>
            </div>
            <div id="screenAndBrandInput">
              <label>Screensize:</label><br>
              <select id="screenSize" name="screenSize" value="All screensizes">
                <option>All screensizes</option>                
                ${screenSizes}                
            </select><br>
              <label>Brand:</label><br>
              <select id="brand" name="brand" value="All brands">
                  <option>All brands</option>                  
                  ${brands}
              </select><br>
            </div>
            <div id="operMemInput">
              <label>Operating memory:</label><br>              
              ${operMems}
            </div>
            <div id="intExtMemCameraInput">
              <label>Internal memory:</label><br>
              <select id="intMem" name="intMem" value="All">
                  <option>All</option>                  
                  ${internalMemories}
              </select><br>                      
              <label>External memory:</label><br>
              <select id="extMem" name="extMem" value="All">
                  <option>All</option>                  
                  ${externalMemories}
              </select><br>
              <label>Camera:</label><br>
              <select id="camera" name="camera" value="All">
                  <option>All</option>                  
                  ${cameras}
              </select><br>
            </div>
            <div id="simCountGpsInput">
              <label>Number of sim cards:</label><br>
              <input type="radio" name="simCount" value="1">
              <label>1</label><br>
              <input type="radio" name="simCount" value="2">
              <label>2</label><br>
              <input type="radio" name="simCount" value="Any">
              <label>Any</label><br>
              <label>Gps:</label><br>
              <input type="checkbox" name="gps" value="true">
              <label>Yes</label><br>
              <input type="checkbox" name="gps" value="false">
              <label>No</label><br>
            </div>
            <input type="submit" id="apply" value="Apply">
          </form>
          <div id="filterBtnContainer">
            <input type="button" id="filterBtn" value="Hide filter">            
          </div>         
        </div>      
        <div id="commodityList">
            ${mobiles}           
            <a href="../../../java/com/mycompany/electronicstore/service/MobileDeviceService.java"></a>
        </div>
        <jsp:include page="shopping-cart.jsp"/> 
        <div id ="overlay"></div>
        <footer>            
          <h1>Contacts:</h1>
          <div id="socialNetworks">
            <a href="https://uk-ua.facebook.com/"><img src="/images/facebook.png" alt="facebook"><div>Facebook</div></a>
            <a href="https://twitter.com/login?lang=uk"><img src="/images/twitter.png" alt="twitter"><div>Twitter</div></a>
            <a href="https://www.viber.com/en/"><img src="/images/viber.png" alt="viber"><div>Viber</div></a>
          </div>
          <h2>Phone:+380977777777</h2>
          <h2>E-mail:electronocwebstore@gmail.com</h2>
        </footer>        
    <script src="/javascript/index.js"></script>
    </body>
</html>
