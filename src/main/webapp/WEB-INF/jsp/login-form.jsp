<%-- 
    Document   : login-form
    Created on : 22 січ. 2021, 21:44:22
    Author     : dmytr
--%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<sec:authorize access="!isAuthenticated()">
    <div class="loginDropdown" sec:authorize access="!isAuthenticated()">
          <button id="loginMenuOpener" class="fas fa-sign-in-alt" >Sign in</button>          
          <div id="loginMenu">
              <a href="/users/create">Registration</a>
              <a href="/oauth2/authorization/okta">Login</a>
          </div>
    </div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <div id="logoutButtonContainer" sec:authorize access=isAuthenticated()">
        <form action="/logout">
            Hello,<sec:authentication property="principal.givenName"/>
            <input type="submit" id="logoutButton" value="Logout"/>
        </form>
    </div>
</sec:authorize>
<div id="loginFormContainer">
        <input type="button" id="closeLoginForm" value="&times;"><br>
        <%
            if(request.getParameter("userNotFound")!=null){
                if(request.getParameter("userNotFound").equals("true")){
                    out.print("<div id=\"userNotFoundErrorMessage\">Wrong email"
                            + " or password.Try again.</div>");
                }
            }
        %>
        <form action="/login" method="POST">
            <table>                
                <tr>
                    <td>
                        <label>Email:</label>
                    </td>
                    <td>
                        <input type="text" name="username" id="username"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Password:</label>
                    </td>
                    <td>
                        <input type="password" id="password" name="password"/>
                    </td>
                </tr>            
            </table>
            <input type="submit" value="Register"/>
            <input type="reset" value="Reset"/>
        </form>
        <p>Don't have an account?Create one <a href="/users/create">here</a></p>
    </div>
