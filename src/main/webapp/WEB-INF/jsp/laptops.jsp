<%-- 
    Document   : laptops
    Created on : 2 лип. 2020, 19:17:40
    Author     : dmytr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laptops</title>
        <link rel="stylesheet" href="\css\index.css">
        <link rel="stylesheet" href="\css\laptops.css">
        <link rel="stylesheet" href="\css\commodity.css">        
    </head>
    <body>
        <div id="headerContainer">
            <jsp:include page="login-form.jsp"/>
          <div id="titleAndButtonContainer">
            <h1 id="title">Laptops</h1>
            <input type="button" id="basket" value="Basket">
          </div>           
          <div id="otherPagesLinks">
              <a href="/index">Home</a>
              <a href="/tv/all">TVs</a>
              <a href="/mobile/all">Mobile phones</a>
              <a href="/laptop/all">Laptops</a>
              <a href="/acc/all">Accesories</a>
          </div>
        </div>         
        <div id="form">
          <form action="/laptop/filter" method="POST">
              <div id="priceInput">
                <label>Price from:</label>
                <span id="minPriceValue"></span><br>                
                <input type="range" name="minPrice" min="0" max="100000" value="0" id="minPrice"><br>
                <label>To:</label>
                <span id="maxPriceValue"></span><br>
                <input type="range" name="maxPrice" min="0" max="100000" value="100000" id="maxPrice"><br>
              </div>
              <div id="screenAndBrandInput">
                <label>Screensize:</label><br>                
                <select id="screenSize" name="screenSize" value="All screenSizes">
                    <option>All screensizes</option>
                    ${screenSizes}                                   
                </select><br>
                <label>Brand:</label><br>
                <select id="brand" name="brand" value="All brands">
                    <option>All brands</option>                    
                    ${brands}
                </select><br>
              </div>
              <div id="resAndIntMemInput">
                <label>Resolution:</label><br>
                <select id="resolution" name="resolution" value="All resolutions">
                    <option>All resolutions</option>                    
                    ${resolutions}
                </select><br>
                <label>Internal memory:</label><br> 
                <select id="intMem" name="intMem" value="All">
                    <option>All</option>                    
                    ${internalMemories}
                </select><br>
              </div>
              <div id="operatingMemoryInput">
                <label>Operating memory:</label><br>
                ${operMems}                
              </div>
              <input type="submit" value="Apply" id="apply">
          </form>
          <div id="filterBtnContainer">
            <input type="button" id="filterBtn" value="Hide filter">            
          </div>           
        </div>      
        <div id="commodityList">
          ${laptops}
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
