package com.vladtam.jspapplication.servlets.advertisement;

import com.vladtam.jspapplication.daos.AdvertisementDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdvertisementListServlet", value = "/AdvertisementListServlet")
public class AdvertisementListServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(AdvertisementListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdvertisementDAO advertisementDAO = new AdvertisementDAO();
        List<BaseModelInterface> advertisementsList = advertisementDAO.getListInfo();
        request.setAttribute("advertisements", advertisementsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/advertisementPages/showAdvertisementsList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}
