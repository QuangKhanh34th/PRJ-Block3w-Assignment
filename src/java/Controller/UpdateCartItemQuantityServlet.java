/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateCartItemQuantityServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<CartItem> userCart = (List<CartItem>) session.getAttribute("cart");
        String itemProdID = request.getParameter("prodID");
        

        //if somehow the cart is empty or not initialized yet when this servlet is called, just go to the view without doing anything
        if (userCart == null || userCart.isEmpty()) {
            request.getRequestDispatcher("view/cart.jsp").forward(request, response);
            return;
        }
        
        int cartItemAmount = Integer.parseInt(request.getParameter("amount"));
        //searching for the correct item in the cart then update its quantity
        int i = 0;
        for (CartItem cartItem : userCart) {
            if (cartItem.getProduct().getProdID().equals(itemProdID)) {
                break;
            }
            i++;
        }
        
        //if amount is 0 or lower, remove the item from the cart
        //else set the cartItem to the desired amount
        if (cartItemAmount <= 0) {
            userCart.remove(i);
        } else {
            userCart.get(i).setAmount(cartItemAmount);
        }
        
        //Update cart information then redirect to view
        session.setAttribute("cart", userCart);
        request.getRequestDispatcher("view/cart.jsp").forward(request, response);
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

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
