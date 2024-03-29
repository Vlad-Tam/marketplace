package com.vladtam.jspapplication.servlets.address;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import com.vladtam.jspapplication.models.City;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCreatingFieldsServlet", value = "/GetCreatingFieldsServlet")
public class GetCreatingAddressFieldsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityDAO cityDAO = new CityDAO();
        List<BaseModelInterface> citiesList = cityDAO.getListInfo();
        request.setAttribute("cities", citiesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/addressPages/createAddress.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
