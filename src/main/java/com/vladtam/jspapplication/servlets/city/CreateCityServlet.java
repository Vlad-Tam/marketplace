package com.vladtam.jspapplication.servlets.city;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.models.City;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateCityServlet", value = "/CreateCityServlet")
public class CreateCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        City city = new City();
        city.setName(request.getParameter("name"));
        city.setRegion(request.getParameter("region"));
        CityDAO cityDAO = new CityDAO();
        cityDAO.createNew(city);
        response.sendRedirect("/cities");
    }
}
