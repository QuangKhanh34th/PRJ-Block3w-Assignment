/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LoginServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("tentk");
        String password = request.getParameter("matkhau");
        String remember = request.getParameter("remember");

        UserDAO dao = new UserDAO();
        User user = dao.checkUser(username, password);

        if (user != null) {
            // Save user info to session based on role
            HttpSession session = request.getSession();
            if (user.getUserGroup().equals("KH")) {
                session.setAttribute("customer", user);
            } else if (user.getUserGroup().equals("AD")) {
                session.setAttribute("admin", user);
            }
            
            // "Remember me" function
            if (remember != null && remember.equals("true")) {
                Cookie ckUser = new Cookie("userC", username);
                Cookie ckPass = new Cookie("passC", password);

                ckUser.setMaxAge(60 * 60 * 24 * 7); // 7 days
                ckPass.setMaxAge(60 * 60 * 24 * 7);

                response.addCookie(ckUser);
                response.addCookie(ckPass);
            }

            response.sendRedirect("MainController");
        } else {
            request.setAttribute("error", "Incorrect username or password");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }
    }

}
