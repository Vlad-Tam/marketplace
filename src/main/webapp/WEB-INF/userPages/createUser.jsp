<%@ page import="com.vladtam.jspapplication.models.Address" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating user</title>
</head>
<body>
    <h1>Creating new user:</h1>
    <form action="/CreateUserServlet" method="post">

        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name"><br>

        <label for="surname">Surname:</label><br>
        <input type="text" id="surname" name="surname"><br>

        <label for="phoneNumber">Phone number:</label><br>
        <input type="text" id="phoneNumber" name="phoneNumber"><br>

        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email"><br>

        <label for="password">Password:</label><br>
        <input type="text" id="password" name="password"><br>

        <label for="address">City:</label><br>
        <select id="address" name="addressId">
            <% for (Address address : (List<Address>) request.getAttribute("addresses")) { %>
            <option value="<%= address.getId() %>"><%= address.getCity().getRegion() + " region, city: " + address.getCity().getName() + ", street: " + address.getStreet() + ", " + address.getHouseNumber() + "-" + address.getFlatNumber()%></option>
            <% } %>
        </select><br>

        <input type="submit" value="Create">
    </form>
</body>
</html>
