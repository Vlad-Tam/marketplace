package com.vladtam.jspapplication.servlets.address;

import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.servlets.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "GetCreatingFieldsServlet", value = "/GetCreatingFieldsServlet")
public class GetCreatingAddressFieldsServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(GetCreatingAddressFieldsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CityDAO cityDAO = new CityDAO();
        BaseServlet.baseListServletMethod(cityDAO, request, response, "cities", "/WEB-INF/addressPages/createAddress.jsp");
    }
}
