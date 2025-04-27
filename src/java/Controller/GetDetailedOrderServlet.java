/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import Model.DetailedOrder;
import Model.Order;
import Model.User;
import java.io.IOException;
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
public class GetDetailedOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String orderID = request.getParameter("orderID");
        User user = (User) session.getAttribute("customer");

        if (orderID != null && !orderID.isEmpty()) {
            OrderDAO orderDAO = new OrderDAO();
            //get the order
            Order order = orderDAO.getOrderByID(orderID);
            
            //if the order Username don't match with the current logged in username
            //redirect back to main page to restrict access to unowned orders
            if (!order.getOrderUser().equals(user.getUsername())) {
                response.sendRedirect(request.getContextPath() + "/MainController");
                return;
            }
            
            //get the products bought in that order
            List<DetailedOrder> detailedList = orderDAO.getDetailedOrderByID(user.getUsername(), orderID);

            if (order != null) {
                request.setAttribute("order", order);
                request.setAttribute("detailedOrders", detailedList);
                request.getRequestDispatcher("view/OrderDetails.jsp").forward(request, response);
            } else {
                response.getWriter().println("No Order found.");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
