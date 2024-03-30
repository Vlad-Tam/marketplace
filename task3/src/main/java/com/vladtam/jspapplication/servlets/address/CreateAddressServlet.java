package com.vladtam.jspapplication.servlets.address;

import com.vladtam.jspapplication.daos.AddressDAO;
import com.vladtam.jspapplication.daos.CityDAO;
import com.vladtam.jspapplication.models.Address;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "CreateAddressServlet", value = "/CreateAddressServlet")
public class CreateAddressServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(CreateAddressServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Address address = new Address();
        CityDAO cityDAO = new CityDAO();
        AddressDAO addressDAO = new AddressDAO();

        try {
            address.setStreet(request.getParameter("street"));
            address.setHouseNumber(Integer.parseInt(request.getParameter("houseNumber")));
            address.setFlatNumber(Integer.parseInt(request.getParameter("flatNumber")));
            address.setCity(cityDAO.getFullInfo(Integer.parseInt(request.getParameter("cityId"))));
            addressDAO.createNew(address);
            response.sendRedirect("/addresses");
        } catch (NumberFormatException e) {
            logger.error("Parameter is not number", e);
        } catch (IOException e) {
            logger.error("Redirect IOException", e);
        }
    }
}
