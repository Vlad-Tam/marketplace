package com.vladtam.jspapplication.servlets.wish;

import com.vladtam.jspapplication.daos.WishDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateWishServlet", value = "/CreateWishServlet")
public class CreateWishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WishDAO wishDAO = new WishDAO();
        wishDAO.createNew(Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("advertisementId")));
        response.sendRedirect(request.getParameter("originalPage"));
    }
}
