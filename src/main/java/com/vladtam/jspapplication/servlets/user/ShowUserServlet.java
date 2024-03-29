package com.vladtam.jspapplication.servlets.user;

import com.vladtam.jspapplication.daos.UserDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "ShowUserServlet", value = "/ShowUserServlet")
public class ShowUserServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(ShowUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String idStr = pathInfo.substring(1);
        try {
            int id = Integer.parseInt(idStr);
            UserDAO userDAO = new UserDAO();
            BaseModelInterface user = userDAO.getFullInfo(id);
            request.setAttribute("userInfo", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userPages/showUser.jsp");
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
