<%@ page import="com.vladtam.jspapplication.models.City" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cities</title>
</head>
<body>
<h1>Choice city:</h1>
<ul>
    <% for (City city : (List<City>) request.getAttribute("cities")) { %>
    <li>
        <a href="<%= "cities/" + city.getId() %>">
            <%= city.getRegion() + " region, city: " + city.getName() %>
        </a>
    </li>
    <% } %>
</ul>
<br>
<h3>
    <a href="/cities/new">Create new city</a></br>
    <a href="../">Return to main menu</a></br>
</h3>
</body>
</html>
