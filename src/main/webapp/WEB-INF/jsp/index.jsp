<%-- 
    Document   : index
    Created on : 24 лист. 2020, 21:48:59
    Author     : dmytr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Electronic Store</title>
        <link rel="stylesheet" href="/css/index.css" type="text/css">        
    </head>
    <body>
        <div id="headerContainer">
            <div id="titleAndButtonContainer">
                <h1 id="title"><img src="images/favicon.png" alt="Electronic Webstore">Electronic Webstore</h1><br>
                <input type="button" id="basket" value="Basket">
            </div>
        </div>
        <div id="links">
          <a href="/tv/all"><img src="images/television-sony-kd45xh80.jpg" alt="TVs"><div>TVs</div></a>
          <a href="/mobile/all"><img src="images/mobiledevice-smartphone.png" alt="Mobile phones"><div>Mobile phones</div></a>
          <a href="/laptop/all"><img src="images/laptop-laptops.jpeg" alt="Laptops"><div>Laptops</div></a>
          <a href="acc/all"><img src="images/acc-accesories.jpg" alt="Accesories"><div>Accesories</div></a>          
        </div>
        <a href="/orders/all">See orders</a>
        <footer>            
          <h1>Contacts:</h1>
          <div id="socialNetworks">
            <a href="https://uk-ua.facebook.com/"><img src="images/facebook.png" alt="facebook"><div>Facebook</div></a>
            <a href="https://twitter.com/login?lang=uk"><img src="images/twitter.png" alt="twitter"><div>Twitter</div></a>
            <a href="https://www.viber.com/en/"><img src="images/viber.png" alt="viber"><div>Viber</div></a>
          </div>          
          <h2>Phone:+380977777777</h2>
          <h2>E-mail:electronocwebstore@gmail.com</h2>
        </footer>
        <div id="popup_window">
            <input type="button" id="close" value="&times;"><br>           
            <div id="addedItems"></div>            
            <form action="/orders/create" method="POST">
                <label>To make an order live us your contact information, please:</label><br>
                <label>Your name:</label>
                <input type="text" name="name" id="name"><br>
                <label>E-mail:</label>
                <input type="text" name="email" id="email"><br>
                <label>Phone:</label>
                <input type="text" name="phone" id="name"><br>
                <input type="submit" id=send value="send">
            </form>
        </div>
        <div id ="overlay"></div>
        <script src="javascript/index.js"></script>
    </body>
</html>
