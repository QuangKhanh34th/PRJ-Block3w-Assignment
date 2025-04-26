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
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/RegisterAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPassword");

        //Validations
        if (username.length() <= 5) {
            request.setAttribute("error", "username must have more than 5 characters, please try again");
            request.getRequestDispatcher("view/RegisterAccount.jsp").forward(request, response);
            return;
        }

        if (password.length() <= 8) {
            request.setAttribute("error", "Password must have more than 8 characters, please try again");
            request.getRequestDispatcher("view/RegisterAccount.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPass)) {
            request.setAttribute("error", "Passwords does not match, please try again");
            request.getRequestDispatcher("view/RegisterAccount.jsp").forward(request, response);
            return;
        }

        User userCheck = userDAO.checkUser(username);
        if (userCheck != null) {
            request.setAttribute("error", "This username has already been taken, please try again");
            request.getRequestDispatcher("view/RegisterAccount.jsp").forward(request, response);
            return;
        }

        //Actual input
        User inputUser = new User(username, password, false, email, "KH");
        if (userDAO.addUser(inputUser) == 0) {
            request.setAttribute("error", "Register failed, try again later");
            request.getRequestDispatcher("view/RegisterAccount.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("UserRegistered", "Account created successfully, please login to proceed");
            response.sendRedirect("MainController?action=login");
        }
    }

}
