package com.vladtam.jspapplication.servlets.advertisement;

import com.vladtam.jspapplication.daos.AdvertisementDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteAdvertisementServlet", value = "/DeleteAdvertisementServlet")
public class DeleteAdvertisementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdvertisementDAO advertisementDAO = new AdvertisementDAO();
        advertisementDAO.delete(Integer.parseInt(request.getParameter("advertisementId")));
        response.sendRedirect("../advertisements");
    }
}
