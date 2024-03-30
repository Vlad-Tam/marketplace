package com.vladtam.jspapplication.servlets.address;

import com.vladtam.jspapplication.daos.AddressDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "ShowAddressServlet", value = "/ShowAddressServlet")
public class ShowAddressServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(ShowAddressServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String idStr = pathInfo.substring(1);
        try {
            int id = Integer.parseInt(idStr);
            AddressDAO addressDAO = new AddressDAO();
            BaseModelInterface address = addressDAO.getFullInfo(id);
            request.setAttribute("addressInfo", address);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/addressPages/showAddress.jsp");
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
