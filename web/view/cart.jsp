<%--
    Document   : cart
    Created on : Apr 27, 2025, 9:45:00 PM
    Author     : Your Name
--%>

<%@page import="Model.CartItem"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart - Game Shop</title>
        <style>
            body {
                font-family: sans-serif;
                background-color: #f4f4f4;
            }
            .container {
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 80%;
                max-width: 900px;
                margin: 20px auto;
            }
            h2 {
                color: #333;
                text-align: center;
                margin-bottom: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }
            .action-buttons a, .action-buttons button {
                display: inline-block;
                padding: 8px 12px;
                margin-right: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                text-decoration: none;
                color: #333;
                background-color: #eee;
                cursor: pointer;
                font-size: 0.9em;
            }
            .action-buttons a:hover, .action-buttons button:hover {
                background-color: #ddd;
            }
            .quantity-input {
                width: 50px;
                padding: 6px;
                border: 1px solid #ccc;
                border-radius: 4px;
                text-align: center;
            }
            .total {
                text-align: right;
                font-weight: bold;
                font-size: 1.1em;
            }
            .empty-cart {
                text-align: center;
                color: #777;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Your Shopping Cart</h2>
            <!-- cart is null or is initialized but somehow empty -->
            <c:if test="${empty sessionScope.cart || sessionScope.cart.isEmpty()}">
                <p class="empty-cart">Your cart is empty.</p>
                <a href="MainController" class="empty-cart" style="text-decoration: none; color: #0099ff">
                    <p >Continue Shopping</p>
                </a>
            </c:if>
            <c:if test="${not empty sessionScope.cart && !sessionScope.cart.isEmpty()}">
                <table>
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Amount</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${sessionScope.cart}">
                            <tr>
                                <td>${item.product.prodName}</td>
                                <td>
                                    <form action="MainController?action=updateCart" method="post">
                                        <input type="hidden" name="prodID" value="${item.product.prodID}">
                                        <input type="number" class="quantity-input" name="amount" value="${item.amount}" min="0">
                                        <button type="submit">Update</button>
                                    </form>
                                </td>
                                <td class="action-buttons">
                                    <a href="MainController?action=removeFromCart&prodID=${item.product.prodID}">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="total">
                    Total Items: ${sessionScope.cart.size()}
                    <%
                        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
                        double totalPrice = 0;
                        if (cartItems != null) {
                            for (CartItem cartItem : cartItems) {
                                totalPrice += cartItem.getProduct().getProdPrice() * cartItem.getAmount();
                            }
                        }
                    %>
                    Total Price: $<%= String.format("%.2f", totalPrice)%>
                </div>
                <div style="text-align: center; margin-top: 20px;">
                    <a href="MainController" style="text-decoration: none; color: #0099ff">
                        Continue Shopping
                    </a>
                    <button style="padding: 10px 20px; background-color: #5cb85c; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; margin-left: 20px;">Checkout</button>
                </div>
            </c:if>
        </div>
    </body>
</html>