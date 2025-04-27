<%-- 
    Document   : OrderFunctions
    Created on : Apr 27, 2025, 11:02:15 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.customer.username}'s Orders</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ActionList.css">
        <style>
            .navigation-links {
                display: flex;
                align-items: center;
                margin-left: 20px; /* Space between website name and links */
            }

            .navigation-links a {
                color: white;
                text-decoration: none;
                margin-right: 15px;
                font-size: 0.95em;
            }

            .navigation-links a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div style="display: flex; align-items: center;">
                <span class="website-name">Game Shop</span>
                <c:if test="${not empty sessionScope.customer}">
                    <div class="navigation-links">
                        <a href="MainController">Product List</a>
                        <a href="MainController?action=viewOrders">Orders</a>
                        <a href="MainController?action=viewCart">My Cart</a>
                    </div>
                </c:if>
            </div>
            <div class="user-controls">
                <c:if test="${empty sessionScope.customer && empty sessionScope.admin}">
                    <a href="MainController?action=login" class="login-button">Login</a>
                </c:if>
                <c:if test="${not empty sessionScope.customer}">
                    <span class="welcome-message">Welcome, ${sessionScope.customer.username}</span>
                    <a href="MainController?action=logout" class="logout-button">Logout</a>
                </c:if>
                <c:if test="${not empty sessionScope.admin}">
                    <span class="welcome-message">Welcome, ${sessionScope.admin.username}</span>
                    <a href="MainController?action=logout" class="logout-button">Logout</a>
                </c:if>
            </div>
        </div>

        <h2>Order List</h2>

        <table border="1" cellpadding="5" cellspacing="0" class="item-list">
            <tr>
                <th>OrderID</th>
                <th>Date ordered</th>
                <th>State</th>
                <th>Action</th>
            </tr>
            <!-- Dùng JSTL để duyệt qua danh sách sản phẩm -->
            <c:forEach var="ord" items="${orderList}">
                <tr class="item">
                    <td>${ord.orderID}</td>
                    <td>${ord.orderTime}</td>
                    <td>${ord.orderState == 1 ? 'Completed' : 'Canceled'}</td>

                    <td>
                        <div class="action">
                            <a href="MainController?action=viewDetails&item=order&orderID=${ord.orderID}">
                                <button class="action-button">Details</button>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
