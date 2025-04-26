<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.product.prodName}'s Information - Game Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
    </head>
    <body>
        <div class="container">
            <h1>Product Details</h1>

            <c:if test="${not empty product}">
                <div class="product-details-wrapper">
                    <div class="product-image-section">
                        <img src="${product.prodImagePath}" alt="${product.prodName} Cover" class="product-image"/>
                    </div>
                    <div class="product-info-section">
                        <div class="detail-row">
                            <span class="detail-label">ProductID:</span>
                            <span class="detail-value">${product.prodID}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Name:</span>
                            <span class="detail-value">${product.prodName}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Description:</span>
                            <span class="detail-value">${product.prodDescription}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">In stock:</span>
                            <span class="detail-value">${product.prodQuantity}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Price:</span>
                            <span class="detail-value">$${product.prodPrice}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Category:</span>
                            <span class="detail-value">${product.prodCategory}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Supplier:</span>
                            <span class="detail-value">${product.prodSupplier}</span>
                        </div>
                        <div class="actions">
                            <c:if test="${sessionScope.user.userGroup == 'KH'}">
                                <a href="#">
                                    <button class="add-to-cart-button">Add to Cart</button>
                                </a>
                            </c:if>
                            <a href="MainController" class="back-link">Back to Product List</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty product}">
                <p class="error-message">Product not found.</p>
                <a href="MainController" class="back-link">Back to Product List</a>
            </c:if>
        </div>
    </body>
</html>