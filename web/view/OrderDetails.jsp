<%-- 
    Document   : OrderDetails
    Created on : Apr 28, 2025, 1:26:26 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Information - Game Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">

        <style>
            .order-details-wrapper {
                display: flex;
                flex-direction: column;
            }

            .order-info-section {
                margin-bottom: 20px;
                padding: 15px;
                border: 1px solid #ddd;
                border-radius: 8px;
                background-color: #f9f9f9;
            }

            .order-info-section .detail-row {
                display: flex;
                margin-bottom: 8px;
            }

            .order-info-section .detail-label {
                font-weight: bold;
                width: 120px;
                color: #555;
            }

            .order-info-section .detail-value {
                color: #333;
            }

            .order-items-section {
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: auto; /* For scrollable table if many items */
            }

            .order-items-table {
                width: 100%;
                border-collapse: collapse;
                background-color: white;
            }

            .order-items-table th, .order-items-table td {
                padding: 10px;
                border-bottom: 1px solid #eee;
                text-align: left;
            }

            .order-items-table th {
                background-color: #f2f2f2;
                font-weight: bold;
                color: #333;
            }

            .order-items-table tr:last-child td {
                border-bottom: none;
            }

            .back-link {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 15px;
                text-decoration: none;
                color: #337ab7;
                border: 1px solid #337ab7;
                border-radius: 5px;
                background-color: white;
            }

            .back-link:hover {
                background-color: #f0f8ff;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Order Details</h1>

            <c:if test="${not empty order}">
                <div class="order-details-wrapper">
                    <div class="order-info-section">
                        <div class="detail-row">
                            <span class="detail-label">Order ID:</span>
                            <span class="detail-value">${order.orderID}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Order Time:</span>
                            <span class="detail-value">${order.orderTime}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Order State:</span>
                            <span class="detail-value">
                                <c:choose>
                                    <c:when test="${order.orderState == 0}">Canceled</c:when>
                                    <c:when test="${order.orderState == 1}">Completed</c:when>
                                </c:choose>
                            </span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Ordered by:</span>
                            <span class="detail-value">${order.orderUser}</span>
                        </div>
                    </div>

                    <h2>Products bought in this Order</h2>
                    <c:if test="${not empty detailedOrders}">
                        <div class="order-items-section">
                            <table class="order-items-table">
                                <thead>
                                    <tr>
                                        <th>Product ID</th>
                                        <th>Amount</th>
                                        <th>Price</th>
                                        <th>Discount</th>
                                        <th>Final Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="detailedOrder" items="${detailedOrders}">
                                        <tr>
                                            <td>${detailedOrder.detailedProdID}</td>
                                            <td>${detailedOrder.detailedAmount}</td>
                                            <td>$${detailedOrder.detailedPrice}</td>
                                            <td>${detailedOrder.detailedDiscount * 100}%</td>
                                            <td>$${detailedOrder.detailedTruePrice}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    <c:if test="${empty detailedOrders}">
                        <p>No products found in this order.</p>
                    </c:if>

                    <a href="MainController?action=viewOrders" class="back-link">Back to Order History</a>
                </div>
            </c:if>

            <c:if test="${empty order}">
                <p class="error-message">Order not found.</p>
                <a href="MainController?action=viewOrders" class="back-link">Back to Order History</a>
            </c:if>
        </div>
    </body>
</html>
