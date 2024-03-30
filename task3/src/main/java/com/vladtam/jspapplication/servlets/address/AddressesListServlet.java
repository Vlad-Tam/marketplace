package com.vladtam.jspapplication.servlets.address;

import com.vladtam.jspapplication.daos.AddressDAO;
import com.vladtam.jspapplication.servlets.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "AddressesListServlet", value = "/AddressesListServlet")
public class AddressesListServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(AddressesListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressDAO addressDAO = new AddressDAO();
        BaseServlet.baseListServletMethod(addressDAO, request, response, "addresses", "/WEB-INF/addressPages/showAddressesList.jsp");
    }
}
