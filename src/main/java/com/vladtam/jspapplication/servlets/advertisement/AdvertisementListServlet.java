package com.vladtam.jspapplication.servlets.advertisement;

import com.vladtam.jspapplication.daos.AddressDAO;
import com.vladtam.jspapplication.daos.AdvertisementDAO;
import com.vladtam.jspapplication.models.Advertisement;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdvertisementListServlet", value = "/AdvertisementListServlet")
public class AdvertisementListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdvertisementDAO advertisementDAO = new AdvertisementDAO();
        List<BaseModelInterface> advertisementsList = advertisementDAO.getListInfo();
        request.setAttribute("advertisements", advertisementsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/advertisementPages/showAdvertisementsList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
