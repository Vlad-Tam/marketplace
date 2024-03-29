<%@ page import="com.vladtam.jspapplication.models.City" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating address</title>
</head>
<body>
    <h1>Creating new address:</h1>
    <form action="/CreateAddressServlet" method="post">
        <label for="city">City:</label><br>
        <select id="city" name="cityId">
            <% for (City city : (List<City>) request.getAttribute("cities")) { %>
            <option value="<%= city.getId() %>"><%= city.getRegion() + " region, city: " + city.getName() %></option>
            <% } %>
        </select><br>
        <label for="street">Street:</label><br>
        <input type="text" id="street" name="street"><br>
        <label for="houseNumber">House number:</label><br>
        <input type="text" id="houseNumber" name="houseNumber"><br>
        <label for="flatNumber">Flat number:</label><br>
        <input type="text" id="flatNumber" name="flatNumber"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>
