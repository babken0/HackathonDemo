package com.example.hackathondemo.controller;

import com.example.hackathondemo.dao.UserDAO;
import com.example.hackathondemo.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/reg")
public class RegServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Successful.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("firstname"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int age = Integer.parseInt(request.getParameter("age"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAge(age);
        user.setUsername(username);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);


        try {
            userDAO.regUser(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Successful.jsp");
        dispatcher.forward(request,response);
    }
}

