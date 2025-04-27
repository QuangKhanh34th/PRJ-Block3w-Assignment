/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.SupplierDAO;
import Model.Category;
import Model.Product;
import Model.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class UpdateProductServlet extends HttpServlet {

    CategoryDAO categoryDAO = new CategoryDAO();
    SupplierDAO supplierDAO = new SupplierDAO();
    ProductDAO productDAO = new ProductDAO();
    List<Category> categories = categoryDAO.getAllCategory();
    List<Supplier> suppliers = supplierDAO.getAllSupplier();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productID = request.getParameter("prodID");
        if (productID != null) {
            Product selectedProduct = productDAO.getProductByID(productID);
            System.out.println("selected prod categoryID: " + selectedProduct.getProdCategory());
            request.setAttribute("categories", categories);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("product", selectedProduct);
            request.getRequestDispatcher("view/UpdateExistingProduct.jsp").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("Invalid ProductID");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("prodID");
        String productName = request.getParameter("prodName");
        String productDescription = request.getParameter("prodDescription");
        //this productCategory is holding categoryID while prodCategory field of the original Product class is holding CategoryName
        //the same for supplier, but this is fine, since we are adding this object to database and not displaying it
        String productCategory = request.getParameter("prodCategory");
        String productSupplier = request.getParameter("prodSupplier");
        String productQuantityStr = request.getParameter("prodQuantity");
        String productPriceStr = request.getParameter("prodPrice");

        //Validations
        String errorMessage = null;

        if (productDescription == null || productDescription.trim().isEmpty()) {
            productDescription = "TBA";
        }
        //use try-catch for setting related error together
        try {
            int quantityTest = Integer.parseInt(productQuantityStr);
            if (quantityTest <= 0) {
                errorMessage = "Product Quantity cannot be zero or negative.";
            }
        } catch (NumberFormatException e) {
            errorMessage = "Invalid Product Quantity format.";
        }

        try {
            double priceTest = Double.parseDouble(productPriceStr);
            if (priceTest < 0.01) {
                errorMessage = "Product Price must be greater than 0.";
            }
        } catch (NumberFormatException e) {
            errorMessage = "Invalid Product Price format.";
        }

        //error found in validation steps
        if (errorMessage != null) {
            Product selectedProduct = productDAO.getProductByID(productID);
            request.setAttribute("product", selectedProduct);
            //Repopulate dropdown lists
            request.setAttribute("categories", categories);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("view/UpdateExistingProduct.jsp").forward(request, response);
            return; //Stop further processing if there are errors
        }

        //Product object modification
        Product selectedProduct = productDAO.getProductByID(productID);
        selectedProduct.setProdName(productName);
        selectedProduct.setProdDescription(productDescription);
        selectedProduct.setProdCategory(productCategory);
        selectedProduct.setProdSupplier(productSupplier);
        selectedProduct.setProdQuantity(Integer.parseInt(productQuantityStr)); //this should return no exception because this line is beyond validation process
        selectedProduct.setProdPrice(Double.parseDouble(productPriceStr)); //also this
        
        //Product database addition 
        if (productDAO.updateProduct(selectedProduct) == 1) {
            //Product updated successfully
            response.sendRedirect(request.getContextPath() + "/MainController");
        } else {
            //Failed to add product
            request.setAttribute("error", "Failed to update product. Please try again.");
            request.setAttribute("categories", categories);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("product", selectedProduct);
            request.getRequestDispatcher("view/UpdateExistingProduct.jsp").forward(request, response);
        }
    }

}
