<%@ page import="com.vladtam.jspapplication.models.Address" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Address</title>
</head>
<body>
    <h1>Address information:</h1>
    <% Address address = (Address) request.getAttribute("addressInfo"); %>
    <h4>ID: <%= address.getId() %></h4>
    <h4>Region: <%= address.getCity().getRegion() %></h4>
    <h4>City: <%= address.getCity().getName() %></h4>
    <h4>Street: <%= address.getStreet() %></h4>
    <h4>House number: <%= address.getHouseNumber() %></h4>
    <h4>Flat number: <%= address.getFlatNumber() %></h4>
<h3>
    <a href="../addresses">Return to addresses list</a>
    <form action="/DeleteAddressServlet" method="post">
        <input type="hidden" name="addressId" value="<%=address.getId()%>" />
        <input type="submit" value="Delete this address" />
    </form>
</h3>
</body>
</html>
