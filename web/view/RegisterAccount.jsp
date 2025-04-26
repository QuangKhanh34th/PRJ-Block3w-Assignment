<%--
    Document   : RegisterAccount
    Created on : Apr 26, 2025, 9:42:45 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Register - Game Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
        <style>
            body {
                font-family: sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center; /* Center horizontally */
                align-items: center; /* Center vertically */
                min-height: 100vh; /* Ensure the body takes up at least the full viewport height */
                margin: 0; /* Remove default body margin */
                box-sizing: border-box; /* Ensure padding doesn't affect overall width */
            }

            .banner {
                background-color: #333; /* Dark background for the banner */
                color: white;
                padding: 20px 0;
                text-align: center; /* Center the text within the banner */
                width: 100%; /* Make the banner span the full width */
                position: fixed; /* Position it at the top */
                top: 0;
                z-index: 10; /* Ensure it's above the container */
            }

            .resetPwd-container {
                background-color: white;
                padding: 20px; /* Reduced padding from 30px to 20px */
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 280px; /* Further reduced width from 300px to 280px */
                text-align: left;
                margin-top: 80px; /* Maintain margin to stay below the banner */
                max-height: 85vh; /* Keep max height for potential overflow */
                overflow-y: auto; /* Keep vertical scrolling for overflow */
            }

            .resetPwd-container label {
                display: block;
                margin-bottom: 6px; /* Slightly reduced label margin */
                font-weight: bold;
                color: #555;
                font-size: 0.95em; /* Slightly smaller label font */
            }

            .resetPwd-container input[type="text"],
            .resetPwd-container input[type="email"],
            .resetPwd-container input[type="password"] {
                width: calc(100% - 12px); /* Adjust for reduced padding */
                padding: 8px; /* Reduced input padding */
                margin-bottom: 15px; /* Reduced input margin */
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 0.9em; /* Slightly smaller input font */
            }

            .resetPwd-container input[type="submit"] {
                background-color: #28a745; /* Green color for register button */
                color: white;
                padding: 10px 15px; /* Reduced button padding */
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 0.9em; /* Slightly smaller button font */
                width: 100%;
            }

            .resetPwd-container input[type="submit"]:hover {
                background-color: #1e7e34;
            }

            .back-to-login {
                margin-top: 15px; /* Reduced margin for back to login */
                text-align: center;
                font-size: 0.85em; /* Slightly smaller back to login font */
            }

            .back-to-login a {
                color: #007bff;
                text-decoration: none;
            }

            .back-to-login a:hover {
                text-decoration: underline;
            }

            .error-message {
                color: red;
                margin-top: 8px; /* Slightly reduced error message margin */
                font-size: 0.9em;
            }
        </style>
    </head>
    <body>
        <div class="banner">
            <h1>
                <a href="MainController" style="text-decoration: none; color: white">Game Shop</a>
            </h1>
        </div>

        <div class="resetPwd-container">
            <form action="MainController?action=registerAccount" method="post">
                <label for="username">Username:</label><br>
                <input type="text" id="username" name="username" required><br><br>

                <label for="email">Email:</label><br>
                <input type="email" id="email" name="email" required><br><br>

                <label for="password">Password:</label><br>
                <input type="password" id="password" name="password" required><br><br>

                <label for="ConfirmPassword">Confirm Password:</label><br>
                <input type="password" id="ConfirmPassword" name="confirmPassword" required><br><br>

                <c:if test="${not empty requestScope.error}">
                    <p class="error-message">${requestScope.error}</p>
                </c:if>

                <input type="submit" value="Create Account">
            </form>

            <div class="back-to-login">
                <a href="MainController?action=login">Back to Login</a>
            </div>
        </div>
    </body>
</html>