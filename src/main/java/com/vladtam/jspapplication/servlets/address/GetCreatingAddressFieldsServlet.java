package com.vladtam.jspapplication.servlets.address;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import com.vladtam.jspapplication.servlets.advertisement.AdvertisementListServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCreatingFieldsServlet", value = "/GetCreatingFieldsServlet")
public class GetCreatingAddressFieldsServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(GetCreatingAddressFieldsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityDAO cityDAO = new CityDAO();
        List<BaseModelInterface> citiesList = cityDAO.getListInfo();
        request.setAttribute("cities", citiesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/addressPages/createAddress.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}
