<%-- 
    Document   : accesories
    Created on : 2 лип. 2020, 19:18:17
    Author     : dmytr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accesories</title>
        <link rel="stylesheet" href="\css\index.css">
        <link rel="stylesheet" href="\css\accesories.css">
        <link rel="stylesheet" href="\css\commodity.css">        
    </head>
    <body>
        <div id="headerContainer">
          <div id="titleAndButtonContainer">
            <h1 id="title">Accesories</h1>
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
          <form action="/acc/filter" method="POST">
            <div id="priceInput">
              <label>Price from:</label>
              <span id="minPriceValue"></span><br>                
              <input type="range" name="minPrice" min="0" max="10000" value="0" id="minPrice"><br>
              <label>To:</label>
              <span id="maxPriceValue"></span><br>
              <input type="range" name="maxPrice" min="0" max="10000"  value="10000" id="maxPrice"><br>
            </div> 
            <div id="brandSelector">
              <label>Brand:</label><br>            
              <select id="brand" name="brand" value="All brands">
                  <option>All brands</option>
                  <!--<option>KOSS</option>
                  <option>Logitech</option>
                  <option>Ergo</option>
                  <option>Belkin</option>-->
                  ${brands}
              </select><br>
            </div>
            <div id="typeInput">
              <label id="typeLabel">Type:</label>
              <!--<div id="computer_mouse">            
                <label>Computer mouse</label>
                <input type="checkbox" name="names" value="Computer mouse">
              </div>
              <div id="keyboard">
                <label>Keyboard</label>
                <input type="checkbox" name="names" value="Keyboard">
              </div>
              <div id="earphones">
                <label>Earphones</label>
                <input type="checkbox" name="names" value="Earphones">
              </div>
              <div id="bag">
                <label>Bag for laptop</label><br>
                <input type="checkbox" name="names" value="Bag for laptop">
              </div>-->
              ${types}
            </div>
            <input type="submit" id="apply" value="Apply">
          </form>
          <div id="filterBtnContainer">
            <input type="button" id="filterBtn" value="Hide filter">            
          </div>        
        </div>      
        <div id="commodityList">
          ${acc}
        </div>
        <div id="popup_window">
            <input type="button"  id="close" value="&times;"><br>            
            <div id="addedItems">Basket is empty</div>            
            <form method="POST">
                <label>To make an order live us your contact information, please:</label><br>
                <label>Your name:</label>
                <input type="text" name="name" id="name"><br>
                <label>E-mail:</label>
                <input type="text" name="email" id="email"><br>
                <label>Phone:</label>
                <input type="text" name="name" id="name"><br>
                <input type="sublmit" id=send value="send">
            </form>
        </div>
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
