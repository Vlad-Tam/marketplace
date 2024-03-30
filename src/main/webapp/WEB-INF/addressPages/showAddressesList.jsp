<%@ page import="com.vladtam.jspapplication.models.Address" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Addresses</title>
</head>
<body>
<h1>Choice address:</h1>
<ul>
    <% for (Address address : (List<Address>) request.getAttribute("addresses")) { %>
        <li>
            <a href="<%= "addresses/" + address.getId() %>">
            <%= address.getCity().getRegion() + " region, city: " + address.getCity().getName() + ", street " +
                address.getStreet() + ", " + address.getHouseNumber() + "-" + address.getFlatNumber() %>
            </a>
        </li>
    <% } %>
</ul>
<h3>
    <a href="/addresses/new">Create new address</a></br>
    <a href="../">Return to main menu</a>
</h3>
</body>
</html>
