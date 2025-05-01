/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.OrderDAO;
import Model.DetailedOrder;
import Model.Order;
import Model.User;
import Model.CartItem;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class CheckoutServlet extends HttpServlet {
   
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
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        User loggedInUser = (User) session.getAttribute("customer");

        
        if (cartItems != null && !cartItems.isEmpty()) {
            OrderDAO orderDAO = new OrderDAO();

            // Create a new Order
            String orderID = "HD" + System.currentTimeMillis(); //for near unique OrderID
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String orderTime = now.format(formatter);
            int orderState = 1; //Default to 1 for "Completed" order for demo purposes
            String orderUser = loggedInUser.getUsername();

            Order newOrder = new Order(orderID, orderTime, orderState, orderUser);
            int orderCreated = orderDAO.createOrder(newOrder);

            //If order created successfully, continue with detailed orders
            if (orderCreated == 1) {
                // Create DetailedOrder records for each item in the cart
                List<DetailedOrder> detailedOrdersList = new ArrayList<>();
                for (CartItem item : cartItems) {
                    DetailedOrder detailedOrder = new DetailedOrder();
                    detailedOrder.setDetailedID(orderID); // use the same orderID because it is a foreign key
                    detailedOrder.setDetailedProdID(item.getProduct().getProdID());
                    detailedOrder.setDetailedAmount(item.getAmount());
                    detailedOrder.setDetailedPrice(item.getProduct().getProdPrice());
                    detailedOrder.setDetailedDiscount(0.0); //0 discount for demo
                    detailedOrder.setDetailedTruePrice(item.getProduct().getProdPrice() * item.getAmount()); // Calculate true price

                    detailedOrdersList.add(detailedOrder);
                }

                int detailedOrdersCreated = orderDAO.createDetailedOrders(detailedOrdersList, orderID);

                if (detailedOrdersCreated > 0) {
                    //Clear the cart from the session after successful checkout
                    session.removeAttribute("cart");
                    //Redirect to a main page
                    response.sendRedirect(request.getContextPath() + "/MainController");
                    return;
                }
            } else {
                // Handle error creating the order
                response.getWriter().println("Error creating the order.");
            }
        } else {
            // Redirect back to the cart page if cart is empty (mostly because user input the url directly on address bar)
            response.sendRedirect("view/cart.jsp"); 
        }
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
