package com.vladtam.jspapplication.servlets.user;

import com.vladtam.jspapplication.daos.AddressDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCreatingUserServlet", value = "/GetCreatingUserServlet")
public class GetCreatingUsersFieldsServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(GetCreatingUsersFieldsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressDAO addressDAO = new AddressDAO();
        List<BaseModelInterface> addressesList = addressDAO.getListInfo();
        request.setAttribute("addresses", addressesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userPages/createUser.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Forward ServletException", e);
        } catch (IOException e) {
            logger.error("Forward IOException", e);
        }
    }
}