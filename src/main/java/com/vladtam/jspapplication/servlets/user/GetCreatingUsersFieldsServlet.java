package com.vladtam.jspapplication.servlets.user;

import com.vladtam.jspapplication.daos.AddressDAO;
import com.vladtam.jspapplication.models.BaseModelInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCreatingUserServlet", value = "/GetCreatingUserServlet")
public class GetCreatingUsersFieldsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressDAO addressDAO = new AddressDAO();
        List<BaseModelInterface> addressesList = addressDAO.getListInfo();
        request.setAttribute("addresses", addressesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userPages/createUser.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
