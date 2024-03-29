package com.vladtam.jspapplication.servlets.user;

import com.vladtam.jspapplication.daos.AddressDAO;
import com.vladtam.jspapplication.daos.UserDAO;
import com.vladtam.jspapplication.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateUserServlet", value = "/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        UserDAO userDAO = new UserDAO();
        AddressDAO addressDAO = new AddressDAO();
        user.getBasicInfo().setName(request.getParameter("name"));
        user.getBasicInfo().setSurname(request.getParameter("surname"));
        user.getBasicInfo().setPhoneNumber(request.getParameter("phoneNumber"));
        user.getBasicInfo().setEmail(request.getParameter("email"));
        user.getBasicInfo().setPassword(request.getParameter("password"));
        user.setAddress(addressDAO.getFullInfo(Integer.parseInt(request.getParameter("addressId"))));
        userDAO.createNew(user);
        response.sendRedirect("/users");
    }
}
