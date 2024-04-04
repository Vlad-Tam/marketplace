package com.vladtam.jspapplication.servlets.advertisement;

import com.vladtam.jspapplication.daos.AdvertisementDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "ShowAdvertisementServlet", value = "/ShowAdvertisementServlet")
public class ShowAdvertisementServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(ShowAdvertisementServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String idStr = pathInfo.substring(1);
        try {
            int id = Integer.parseInt(idStr);
            AdvertisementDAO advertisementDAO = new AdvertisementDAO();
            BaseModelInterface advertisement = advertisementDAO.getFullInfo(id);
            request.setAttribute("advertisementInfo", advertisement);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/advertisementPages/showAdvertisement.jsp");
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
