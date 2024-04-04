package com.vladtam.jspapplication.servlets.city;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.servlets.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "CitiesListServlet", value = "/CitiesListServlet")
public class CitiesListServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(CitiesListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityDAO cityDAO = new CityDAO();
        BaseServlet.baseListServletMethod(cityDAO, request, response, "cities", "/WEB-INF/cityPages/showCitiesList.jsp");
    }
}
