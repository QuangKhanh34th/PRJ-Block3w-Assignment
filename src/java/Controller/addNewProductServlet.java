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
public class addNewProductServlet extends HttpServlet {

    CategoryDAO categoryDAO = new CategoryDAO();
    SupplierDAO supplierDAO = new SupplierDAO();
    List<Category> categories = categoryDAO.getAllCategory();
    List<Supplier> suppliers = supplierDAO.getAllSupplier();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categories", categories);
        request.setAttribute("suppliers", suppliers);
        request.getRequestDispatcher("view/AddNewProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("prodID");
        String productName = request.getParameter("prodName");
        //this productCategory is holding categoryID while prodCategory field of the original Product class is holding CategoryName
        //the same for supplier, but this is fine, since we are adding this object to database and not displaying it
        String productDescription = request.getParameter("prodDescription");
        String productCategory = request.getParameter("prodCategory");
        String productSupplier = request.getParameter("prodSupplier");
        String productQuantityStr = request.getParameter("prodQuantity");
        String productPriceStr = request.getParameter("prodPrice");

        //Validations
        String errorMessage = null;

        ProductDAO productDAO = new ProductDAO();
        Product testProduct = productDAO.getProductByID(productID);
        if (testProduct != null) {
            errorMessage = "ProductID already exist in database";
        }

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

        if (errorMessage != null) {
            //Repopulate dropdown lists
            request.setAttribute("categories", categories);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("view/AddNewProduct.jsp").forward(request, response);
            return; //Stop further processing if there are errors
        }

        //Product object creation
        Product newProduct = new Product();
        newProduct.setProdID(productID);
        newProduct.setProdName(productName);
        newProduct.setProdDescription(productDescription);
        newProduct.setProdImagePath("resources/GameCover/Placeholder.jpg");
        newProduct.setProdCategory(productCategory);
        newProduct.setProdSupplier(productSupplier);
        newProduct.setProdQuantity(Integer.parseInt(productQuantityStr)); //this should return no exception because this line is beyond validation process
        newProduct.setProdPrice(Double.parseDouble(productPriceStr)); //also this

        //Product database addition 
        if (productDAO.addProduct(newProduct) == 1) {
            //Product added successfully
            response.sendRedirect(request.getContextPath() + "/MainController");
        } else {
            //Failed to add product
            request.setAttribute("error", "Failed to add new product. Please try again.");
            request.setAttribute("categories", categories);
            request.setAttribute("suppliers", suppliers);
            request.getRequestDispatcher("view/AddNewProduct.jsp").forward(request, response);
        }
    }
}
