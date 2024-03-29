<%@ page import="com.vladtam.jspapplication.models.City" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>City</title>
</head>
<body>
    <h1>City information:</h1>
    <% City city = (City) request.getAttribute("cityInfo"); %>
    <h4>ID: <%= city.getId() %></h4>
    <h4>Region: <%= city.getRegion() %></h4>
    <h4>City: <%= city.getName() %></h4>
<h3>
    <a href="../cities">Return to cities list</a>
    <form action="/DeleteCityServlet" method="post">
        <input type="hidden" name="cityId" value="<%=city.getId()%>" />
        <input type="submit" value="Delete this city" />
    </form>
</h3>
</body>
</html>
