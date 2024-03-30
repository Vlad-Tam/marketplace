package com.vladtam.jspapplication.servlets.advertisement;

import com.vladtam.jspapplication.daos.AdvertisementDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "DeleteAdvertisementServlet", value = "/DeleteAdvertisementServlet")
public class DeleteAdvertisementServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(DeleteAdvertisementServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdvertisementDAO advertisementDAO = new AdvertisementDAO();
        try {
            advertisementDAO.delete(Integer.parseInt(request.getParameter("advertisementId")));
            response.sendRedirect("../advertisements");
        } catch (NumberFormatException e) {
            logger.error("Parameter is not number", e);
        } catch (IOException e) {
            logger.error("Redirect IOException", e);
        }
    }
}
