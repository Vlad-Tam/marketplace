package com.vladtam.jspapplication.servlets.address;

import com.vladtam.jspapplication.daos.AddressDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "DeleteAddressServlet", value = "/DeleteAddressServlet")
public class DeleteAddressServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(DeleteAddressServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressDAO addressDAO = new AddressDAO();
        try {
            addressDAO.delete(Integer.parseInt(request.getParameter("addressId")));
            response.sendRedirect("../addresses");
        } catch (NumberFormatException e) {
            logger.error("Parameter is not number", e);
        } catch (IOException e) {
            logger.error("Redirect IOException", e);
        }
    }
}
