<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login - Game Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
    </head>
    <body>
        <div class="banner">
            <h1>
                <a href="MainController" style="text-decoration: none; color: white">Game Shop</a>
            </h1>
        </div>

         <div class="login-container">
            <form action="MainController?action=login" method="post">
                <label for="tentk">Username:</label><br>
                <input type="text" id="tentk" name="tentk"
                       value="${cookie.userC.value != null ? cookie.userC.value : ''}" required><br><br>

                <label for="matkhau">Password:</label><br>
                <input type="password" id="matkhau" name="matkhau"
                       value="${cookie.passC.value != null ? cookie.passC.value : ''}" required><br><br>

                <label>
                    <input type="checkbox" name="remember" value="true"
                           <c:if test="${cookie.userC.value != null}">
                               checked
                           </c:if>
                    > Remember me
                </label><br><br>

                <c:if test="${not empty error}">
                    <p class="error-message">${error}</p>
                </c:if>

                <input type="submit" value="Login">
            </form>

            <div class="create-account">
                <a href="MainController?action=registerAccount">Create Account</a>
            </div>
        </div>

    </body>
</html>
