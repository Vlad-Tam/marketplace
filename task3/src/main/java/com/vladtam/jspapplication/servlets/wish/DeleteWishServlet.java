package com.vladtam.jspapplication.servlets.wish;

import com.vladtam.jspapplication.daos.WishDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteWishServlet", value = "/DeleteWishServlet")
public class DeleteWishServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(DeleteWishServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WishDAO wishDAO = new WishDAO();
        List<String> allowedHosts = new ArrayList<>();
        allowedHosts.add("/wishes");
        allowedHosts.add("/advertisements/*");
        allowedHosts.add("/users/*");

        try {
            wishDAO.delete(Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("advertisementId")));
            String path = request.getParameter("originalPage");
            if (allowedHosts.contains(path) || isGoodDeletePath(path))
                response.sendRedirect(path);
        } catch (NumberFormatException e) {
            logger.error("Parameter is not number", e);
        } catch (IOException e) {
            logger.error("Redirect IOException", e);
        }
    }

    private boolean isGoodDeletePath(String path) {
        if (path.startsWith("/users/")) {
            String idPart = path.substring("/users/".length());
            return idPart.matches("\\d+");
        }else if(path.startsWith("/advertisements/")) {
            String idPart = path.substring("/advertisements/".length());
            return idPart.matches("\\d+");
        }
        return false;
    }
}
