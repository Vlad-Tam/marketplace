package com.vladtam.jspapplication.servlets.user;

import com.vladtam.jspapplication.daos.UserDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersListServlet", value = "/UsersListServlet")
public class UsersListServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(UsersListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<BaseModelInterface> usersList = userDAO.getListInfo();
        request.setAttribute("users", usersList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userPages/showUsersList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}
