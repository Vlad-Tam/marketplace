package com.vladtam.jspapplication.servlets.advertisement;

import com.vladtam.jspapplication.daos.CategoryDAO;
import com.vladtam.jspapplication.daos.UserDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCreatingAdvertisementFieldsServlet", value = "/GetCreatingAdvertisementFieldsServlet")
public class GetCreatingAdvertisementFieldsServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(GetCreatingAdvertisementFieldsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<BaseModelInterface> usersList = userDAO.getListInfo();
        request.setAttribute("users", usersList);
        CategoryDAO categoryDAO = new CategoryDAO();
        List<BaseModelInterface> categoriesList = categoryDAO.getListInfo();
        request.setAttribute("categories", categoriesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/advertisementPages/createAdvertisement.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}
