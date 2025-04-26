/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class MainController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String HOME_PAGE = "/home"; // Trang chủ mặc định
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";
    private static final String REGISTER_ACCOUNT = "/register";
    private static final String RESET_PASSWORD = "/resetPwd";
    private static final String PRODUCT_DETAILS = "/details";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = HOME_PAGE; // Mặc định là trang home
        try {
            String action = request.getParameter("action");

            if (action == null) {
                url = HOME_PAGE;
            } else {
                switch (action) {
                    // Các hành động khác có thể được thêm vào đây nếu cần
                    case "login": {
                        url = LOGIN;  // mapping servlet LoginServlet
                        break;
                    }
                    
                    case "viewDetails": {
                        url = PRODUCT_DETAILS;
                        break;
                    }
                    
                    case "logout": {
                        url = LOGOUT;
                        break;
                    }
                    
                    case "registerAccount": {
                        url = REGISTER_ACCOUNT;
                        break;
                    }
                    
                    case "resetPassword": {
                        url = RESET_PASSWORD;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at MainControllerServlet: " + e.toString());
        } finally {
            System.out.println("[MainController.java] assigned url: " + url);
            request.getRequestDispatcher(url).forward(request, response);
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
