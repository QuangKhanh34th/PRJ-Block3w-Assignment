/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class ResetUserPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/ResetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();

        String username = request.getParameter("username");
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmNewPassword");

        //Validations
        User userCheck = userDAO.checkUser(username);
        if (userCheck == null) {
            request.setAttribute("error", "User doesn't exist in database, please try again");
            request.getRequestDispatcher("view/ResetPassword.jsp").forward(request, response);
            return;
        }
        
        if (userCheck != null && !oldPass.equals(userCheck.getPassword())) {
            request.setAttribute("error", "Incorrect user password, please try again");
            request.getRequestDispatcher("view/ResetPassword.jsp").forward(request, response);
            return;
        }
        
        if (newPass.length() <= 8) {
            request.setAttribute("error", "Password must have more than 8 characters, please try again");
            request.getRequestDispatcher("view/ResetPassword.jsp").forward(request, response);
            return;
        }

        if (!newPass.equals(confirmPass)) {
            request.setAttribute("error", "Passwords does not match, please try again");
            request.getRequestDispatcher("view/ResetPassword.jsp").forward(request, response);
            return;
        }
        
        if (newPass.equals(oldPass)) {
            request.setAttribute("error", "New password cannot be the same as old password, please try again");
            request.getRequestDispatcher("view/ResetPassword.jsp").forward(request, response);
            return;
        }
        
        //Actual process
        if (userDAO.resetUserPassword(username, newPass) == 0) {
            request.setAttribute("error", "Password reset failed, try again later");
            request.getRequestDispatcher("view/ResetPassword.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("PasswordReset", "Password reset successfully, please login to proceed");
            response.sendRedirect("MainController?action=login");
        }
        
    }
}
