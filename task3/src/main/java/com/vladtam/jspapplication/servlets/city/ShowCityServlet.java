package com.vladtam.jspapplication.servlets.city;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "ShowCityServlet", value = "/ShowCityServlet")
public class ShowCityServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(ShowCityServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String idStr = pathInfo.substring(1);
        try {
            int id = Integer.parseInt(idStr);
            CityDAO cityDAO = new CityDAO();
            BaseModelInterface city = cityDAO.getFullInfo(id);
            request.setAttribute("cityInfo", city);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cityPages/showCity.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            logger.error("Parameter is not number", e);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}
