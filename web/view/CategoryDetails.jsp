<%-- 
    Document   : CategoryDetails.jsp
    Created on : Apr 27, 2025, 2:53:19 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category Information - Game Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
    </head>
    <body>
        <div class="container">
            <h1>Category Details</h1>

            <c:if test="${not empty requestScope.category}">
                <div class="category-details-wrapper">
                    <div class="category-info-section">
                        <div class="detail-row">
                            <span class="detail-label">CategoryID</span>
                            <span class="detail-value">${category.categoryID}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Name:</span>
                            <span class="detail-value">${category.categoryName}</span>
                        </div>
                        <div class="actions">
                            <a href="MainController?action=manageCategory" class="back-link">Back to Category List</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty requestScope.category}">
                <p class="error-message">Category not found.</p>
                <a href="MainController?action=manageCategory" class="back-link">Back to Category List</a>
            </c:if>
        </div>
    </body>
</html>
