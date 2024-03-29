package com.vladtam.jspapplication.servlets.wish;

import com.vladtam.jspapplication.daos.UserDAO;
import com.vladtam.jspapplication.daos.WishDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import com.vladtam.jspapplication.models.Wish;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WishesListServlet", value = "/WishesListServlet")
public class WishesListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WishDAO wishDAO = new WishDAO();
        List<Wish> wishesList = wishDAO.getListInfo();
        request.setAttribute("wishes", wishesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/wishPages/showWishesList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
