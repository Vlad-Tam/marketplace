package com.vladtam.jspapplication.servlets.advertisement;

import com.vladtam.jspapplication.daos.AdvertisementDAO;
import com.vladtam.jspapplication.daos.CategoryDAO;
import com.vladtam.jspapplication.daos.UserDAO;
import com.vladtam.jspapplication.models.Advertisement;
import com.vladtam.jspapplication.servlets.city.DeleteCityServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "CreateAdvertisementServlet", value = "/CreateAdvertisementServlet")
public class CreateAdvertisementServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(CreateAdvertisementServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Advertisement advertisement = new Advertisement();
        AdvertisementDAO advertisementDAO = new AdvertisementDAO();
        UserDAO userDAO = new UserDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        try {
            advertisement.getBasicInfo().setName(request.getParameter("name"));
            advertisement.getBasicInfo().setDescription(request.getParameter("description"));
            advertisement.getBasicInfo().setPrice(Double.valueOf(request.getParameter("price")));
            advertisement.setCategory(categoryDAO.getFullInfo(Integer.parseInt(request.getParameter("categoryId"))));
            advertisement.setVendor(userDAO.getFullInfo(Integer.parseInt(request.getParameter("sellerId"))));
            advertisement.setSaleStatus(Boolean.FALSE);
            advertisementDAO.createNew(advertisement);
            response.sendRedirect("/advertisements");
        } catch (NumberFormatException e) {
            logger.error("Parameter is not number", e);
        } catch (IOException e) {
            logger.error("Redirect IOException", e);
        }
    }
}
