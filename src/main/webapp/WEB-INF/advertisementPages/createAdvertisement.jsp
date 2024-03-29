<%@ page import="com.vladtam.jspapplication.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vladtam.jspapplication.models.Category" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 28.03.2024
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating advertisement</title>
</head>
<body>
<h1>Creating new advertisement:</h1>
<form action="/CreateAdvertisementServlet" method="post">

    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description"><br>

    <label for="price">Price:</label><br>
    <input type="text" id="price" name="price"><br>

    <label for="category">Category:</label><br>
    <select id="category" name="categoryId">
        <% for (Category category : (List<Category>) request.getAttribute("categories")) { %>
        <option value="<%= category.getId() %>"><%= category.getName()%></option>
        <% } %>
    </select><br>

    <label for="seller">Seller:</label><br>
    <select id="seller" name="sellerId">
        <% for (User user : (List<User>) request.getAttribute("users")) { %>
        <option value="<%= user.getId() %>"><%= user.getBasicInfo().getSurname() + " " + user.getBasicInfo().getName()%></option>
        <% } %>
    </select><br>

    <input type="submit" value="Create">
</form>
</body>
</html>
