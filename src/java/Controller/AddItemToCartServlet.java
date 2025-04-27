/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.ProductDAO;
import Model.CartItem;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class AddItemToCartServlet extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //Check if user's cart information is already in the session
        HttpSession session = request.getSession();
        List<CartItem> userCart = null;
        if (session.getAttribute("cart") == null) {
            userCart = new ArrayList<>();
        } else {
            userCart = (List<CartItem>) session.getAttribute("cart");
        }
        
        //fetching product infos
        String prodID = request.getParameter("prodID");
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductByID(prodID);
        
        //Check if the product is already in the cart
        boolean productExists = false;
        for (CartItem item : userCart) {
            if (item.getProduct().getProdID().equals(prodID)) {
                //Product found, increment the amount
                item.setAmount(item.getAmount() + 1);
                productExists = true;
                break; //Exit the loop as the item is updated
            }
        }

        //If the product is not already in the cart, add a new CartItem
        if (!productExists) {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setAmount(1); //Initial amount is 1
            userCart.add(newItem);
        }
        
        //Update the cart in the session
        session.setAttribute("cart", userCart);
        response.sendRedirect(request.getContextPath() + "/MainController");
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
// </editor-fold>

}
