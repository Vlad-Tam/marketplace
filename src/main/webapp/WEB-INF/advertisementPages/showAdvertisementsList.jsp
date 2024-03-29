<%@ page import="com.vladtam.jspapplication.models.Advertisement" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 28.03.2024
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advertisements</title>
</head>
<body>
<h1>Choice advertisement:</h1>
<ul>
    <% for (Advertisement advertisement : (List<Advertisement>) request.getAttribute("advertisements")) { %>
    <li>
        <a href="<%= "advertisements/" + advertisement.getId() %>">
            <%= advertisement.getBasicInfo().getName() + ", price: " + advertisement.getBasicInfo().getPrice() %>
        </a>
    </li>
    <% } %>
</ul>
<h3>
    <a href="/advertisements/new">Create new advertisement</a></br>
    <a href="../">Return to main menu</a>
</h3>
</body>
</html>
