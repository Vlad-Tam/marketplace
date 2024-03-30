package com.vladtam.jspapplication.servlets;

import com.vladtam.jspapplication.daos.BaseDAOInterface;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class BaseServlet {
    public static final Logger logger = LoggerFactory.getLogger(BaseServlet.class);

    private BaseServlet(){}

    public static void baseListServletMethod(BaseDAOInterface baseDAO, HttpServletRequest request, HttpServletResponse response, String attributeName, String pagePath){
        List<BaseModelInterface> baseList = baseDAO.getListInfo();
        request.setAttribute(attributeName, baseList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(pagePath);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}
