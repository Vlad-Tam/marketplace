package com.vladtam.jspapplication.servlets.user;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.daos.UserDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersListServlet", value = "/UsersListServlet")
public class UsersListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<BaseModelInterface> usersList = userDAO.getListInfo();
        request.setAttribute("users", usersList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userPages/showUsersList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
