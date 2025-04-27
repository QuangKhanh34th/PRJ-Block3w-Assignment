<%-- 
    Document   : UpdateExistingProduct
    Created on : Apr 27, 2025, 6:56:08 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Existing Product - Game Shop</title>
        <style>
            body {
                font-family: sans-serif;
                background-color: #f4f4f4;
            }
            .container {
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 60%; /* Adjust width as needed */
                max-width: 800px;
                margin: 20px auto;
            }

            h2 {
                color: #333;
                margin-bottom: 20px;
                text-align: center;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                color: #555;
            }

            .form-group input[type="text"],
            .form-group input[type="number"],
            .form-group select {
                width: calc(100% - 12px);
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 1em;
            }

            .form-group textarea {
                width: calc(100% - 12px);
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 1em;
                resize: vertical; /* Allow vertical resizing */
            }

            .error-message {
                color: red;
                margin-top: 10px;
            }

            .button-container {
                text-align: center;
                margin-top: 20px;
            }

            .button-container button {
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 1em;
                margin: 0 10px;
            }

            .button-container button:hover {
                background-color: #0056b3;
            }

            .button-container a {
                text-decoration: none;
                color: #333;
                padding: 10px 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 1em;
                margin: 0 10px;
            }

            .button-container a:hover {
                background-color: #f0f0f0;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Update Existing Product</h2>
            <c:if test="${not empty requestScope.error}">
                <p class="error-message">${requestScope.error}</p>
            </c:if>
            <form action="MainController?action=updateProduct" method="post">
                <div class="form-group">
                    <label for="prodID">Product ID:</label>
                    <input type="text" id="prodID" name="prodID" value="${requestScope.product.prodID}" required readonly>
                    <small style="color: rgba(0, 0, 0, 0.5);">Product ID cannot be changed.</small>
                </div>
                <div class="form-group">
                    <label for="prodName">Product Name:</label>
                    <input type="text" id="prodName" name="prodName" value="${requestScope.product.prodName}" required>
                </div>
                <div class="form-group">
                    <label for="prodDescription">Product Description (Optional):</label>
                    <textarea id="prodDescription" name="prodDescription">${requestScope.product.prodDescription}</textarea>
                </div>
                <div class="form-group">
                    <label for="prodCategory">Product Category:</label>
                    <select id="prodCategory" name="prodCategory" required>
                        <option value="">-- Select Category --</option>
                        <c:forEach var="category" items="${requestScope.categories}">
                            <option value="${category.categoryID}" ${category.categoryName == requestScope.product.prodCategory ? 'selected' : ''}>${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="prodSupplier">Product Supplier:</label>
                    <select id="prodSupplier" name="prodSupplier" required>
                        <option value="">-- Select Supplier --</option>
                        <c:forEach var="supplier" items="${requestScope.suppliers}">
                            <option value="${supplier.supplierID}" ${supplier.supplierName == requestScope.product.prodSupplier ? 'selected' : ''}>${supplier.supplierName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="prodQuantity">Product Quantity:</label>
                    <input type="number" id="prodQuantity" name="prodQuantity" min="0" value="${requestScope.product.prodQuantity}" required>
                </div>
                <div class="form-group">
                    <label for="prodPrice">Product Price:</label>
                    <input type="number" id="prodPrice" name="prodPrice" min="0.01" step="0.01" value="${requestScope.product.prodPrice}" required>
                </div>
                <div class="button-container">
                    <button type="submit">Update Product</button>
                    <a href="MainController?action=productList">Cancel</a>
                </div>
            </form>
        </div>
    </body>
</html>
