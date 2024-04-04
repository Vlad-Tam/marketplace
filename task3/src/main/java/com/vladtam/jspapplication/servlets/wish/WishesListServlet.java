package com.vladtam.jspapplication.servlets.wish;

import com.vladtam.jspapplication.daos.WishDAO;
import com.vladtam.jspapplication.models.Wish;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WishesListServlet", value = "/WishesListServlet")
public class WishesListServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(WishesListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WishDAO wishDAO = new WishDAO();
        List<Wish> wishesList = wishDAO.getListInfo();
        request.setAttribute("wishes", wishesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/wishPages/showWishesList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}
