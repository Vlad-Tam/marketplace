package com.vladtam.jspapplication.servlets.city;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.servlets.user.DeleteUserServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "DeleteCityServlet", value = "/DeleteCityServlet")
public class DeleteCityServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(DeleteCityServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityDAO cityDAO = new CityDAO();
        try{
        cityDAO.delete(Integer.parseInt(request.getParameter("cityId")));
        response.sendRedirect("../cities");
        } catch (NumberFormatException e) {
            logger.error("Parameter is not number", e);
        } catch (IOException e) {
            logger.error("Redirect IOException", e);
        }
    }
}
