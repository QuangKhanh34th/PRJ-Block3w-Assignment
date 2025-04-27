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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
    private static final String ADD_PRODUCT = "/addNewProductServlet";
    private static final String REMOVE_PRODUCT = "/DeleteProductServlet";
    private static final String UPDATE_PRODUCT = "/UpdateProductServlet";
    private static final String MANAGE_CATEGORY = "/GetCategoryListServlet";
    private static final String CATEGORY_DETAILS = "/GetCategoryDetailsServlet";
    private static final String MANAGE_SUPPLIER = "/GetSupplierListServlet";
    private static final String SUPPLIER_DETAILS = "/GetSupplierDetailsServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = HOME_PAGE;
        try {
            String action = request.getParameter("action");

            if (action == null) {
                url = HOME_PAGE;
            } else {
                switch (action) {
                    case "login": {
                        url = LOGIN;
                        break;
                    }

                    //go to different "view details" servlet based on the type of item get
                    case "viewDetails": {
                        String target = request.getParameter("item");
                        
                        switch (target) {
                            case "product": {
                                url = PRODUCT_DETAILS;
                                break;
                            }

                            case "category": {
                                url = CATEGORY_DETAILS;
                                break;
                            }

                            case "supplier": {
                                url = SUPPLIER_DETAILS;
                                break;
                            }
                        }
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

                    case "manageCategory": {
                        url = MANAGE_CATEGORY;
                        break;
                    }

                    case "manageSupplier": {
                        url = MANAGE_SUPPLIER;
                        break;
                    }
                    
                    case "addProduct": {
                        url = ADD_PRODUCT;
                        break;
                    }
                    
                    case "deleteProduct": {
                        url =REMOVE_PRODUCT;
                        break;
                    }
                    
                    case "updateProduct": {
                        url = UPDATE_PRODUCT;
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
