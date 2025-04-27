<%-- 
    Document   : home
    Created on : Apr 21, 2025, 10:40:11 PM
    Author     : PHAN HAI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ActionList.css">
        <style>
            .admin-links {
                display: flex;
                align-items: center;
                margin-left: 20px; /* Space between website name and links */
            }

            .admin-links a {
                color: white;
                text-decoration: none;
                margin-right: 15px;
                font-size: 0.95em;
            }

            .admin-links a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div style="display: flex; align-items: center;">
                <span class="website-name">Game Shop</span>
                <c:if test="${not empty sessionScope.admin}">
                    <div class="admin-links">
                        <a href="MainController">Manage Product</a>
                        <a href="MainController?action=manageCategory">Manage Category</a>
                        <a href="MainController?action=manageSupplier">Manage Supplier</a>
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

        <h2>Product List</h2>

        <c:if test="${not empty sessionScope.admin}">
            <div class="action">
                <a href="MainController?action=addProduct">
                    <button class="action-button" style="margin-bottom: 20px">Add new Product</button>
                </a>
            </div>
        </c:if>
            
        <table border="1" cellpadding="5" cellspacing="0" class="item-list">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Description</th>
                <th>In stock</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <!-- Dùng JSTL để duyệt qua danh sách sản phẩm -->
            <c:forEach var="sp" items="${dsSanPham}">
                <tr class="item">
                    <td>${sp.prodID}</td>
                    <td>${sp.prodName}</td>
                    <td>${sp.prodDescription}</td>
                    <td>${sp.prodQuantity}</td>
                    <td>${sp.prodPrice}</td>
                    <td>
                        <div class="action">
                            <!-- Nút chi tiết, chuyển hướng đến MainControllerServlet với action = detail và masp -->
                            <a href="MainController?action=viewDetails&item=product&masp=${sp.prodID}">
                                <button class="action-button">Details</button>
                            </a>
                        </div>

                        <c:if test="${not empty sessionScope.customer}">
                            <div class="action">
                                <a href="#">
                                    <button class="action-button">Add to Cart</button>
                                </a>
                            </div>
                        </c:if>
                        <c:if test="${not empty sessionScope.admin}">
                            <div class="action">
                                <a href="MainController?action=updateProduct&prodID=${sp.prodID}">
                                    <button class="action-button" style="background-color: #007bff">Update</button>
                                </a>
                            </div>
                        </c:if>

                        <c:if test="${not empty sessionScope.admin}">
                            <div class="action">
                                <a href="MainController?action=deleteProduct&prodID=${sp.prodID}">
                                    <button class="action-button" style="background-color: #dc3545">Delete</button>
                                </a>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>



    </body>
</html>
