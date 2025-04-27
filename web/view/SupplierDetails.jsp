<%-- 
    Document   : SupplierDetails
    Created on : Apr 27, 2025, 3:44:44 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supplier Details - Game Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/details.css">
    </head>
    <body>
        <div class="container">
            <h1>Supplier Details</h1>

            <c:if test="${not empty requestScope.supplier}">
                <div class="supplier-details-wrapper">
                    <div class="supplier-info-section">
                        <div class="detail-row">
                            <span class="detail-label">SupplierID</span>
                            <span class="detail-value">${supplier.supplierID}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Supplier Name:</span>
                            <span class="detail-value">${supplier.supplierName}</span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Address:</span>
                            <span class="detail-value">${supplier.supplierAddress}</span>
                        </div>
                        <div class="actions">
                            <a href="MainController?action=manageSupplier" class="back-link">Back to Supplier List</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty requestScope.supplier}">
                <p class="error-message">Category not found.</p>
                <a href="MainController?action=manageSupplier" class="back-link">Back to Supplier List</a>
            </c:if>
        </div>
    </body>
</html>
