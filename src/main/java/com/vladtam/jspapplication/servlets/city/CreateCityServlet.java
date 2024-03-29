package com.vladtam.jspapplication.servlets.city;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.models.City;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "CreateCityServlet", value = "/CreateCityServlet")
public class CreateCityServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(CreateCityServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        City city = new City();
        city.setName(request.getParameter("name"));
        city.setRegion(request.getParameter("region"));
        CityDAO cityDAO = new CityDAO();
        cityDAO.createNew(city);
        try {
            response.sendRedirect("/cities");
        }catch (IOException e){
            logger.error("Redirect IOException", e);
        }
    }
}
