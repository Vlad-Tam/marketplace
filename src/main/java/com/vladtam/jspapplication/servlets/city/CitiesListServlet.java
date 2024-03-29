package com.vladtam.jspapplication.servlets.city;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import com.vladtam.jspapplication.servlets.user.GetCreatingUsersFieldsServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CitiesListServlet", value = "/CitiesListServlet")
public class CitiesListServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(CitiesListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityDAO cityDAO = new CityDAO();
        List<BaseModelInterface> citiesList = cityDAO.getListInfo();
        request.setAttribute("cities", citiesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cityPages/showCitiesList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}
