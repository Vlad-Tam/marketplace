package com.vladtam.jspapplication.servlets.wish;

import com.vladtam.jspapplication.daos.AddressDAO;
import com.vladtam.jspapplication.daos.AdvertisementDAO;
import com.vladtam.jspapplication.daos.UserDAO;
import com.vladtam.jspapplication.models.Advertisement;
import com.vladtam.jspapplication.models.BaseModelInterface;
import com.vladtam.jspapplication.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCreatingWishesFieldsServlet", value = "/GetCreatingWishesFieldsServlet")
public class GetCreatingWishesFieldsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        AdvertisementDAO advertisementDAO = new AdvertisementDAO();

        request.setAttribute("originalPage", request.getParameter("originalPage"));
        if (request.getParameter("userId") == null) {
            List<BaseModelInterface> users = userDAO.getListInfo();
            request.setAttribute("users", users);
        }else{
            request.setAttribute("user", userDAO.getFullInfo(Integer.parseInt(request.getParameter("userId"))));
        }

        if (request.getParameter("advertisementId") == null) {
            List<BaseModelInterface> advertisements = advertisementDAO.getListInfo();
            request.setAttribute("advertisements", advertisements);
        }else{
            request.setAttribute("advertisement", advertisementDAO.getFullInfo(Integer.parseInt(request.getParameter("advertisementId"))));
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/wishPages/createWish.jsp");
        dispatcher.forward(request, response);
    }
}
