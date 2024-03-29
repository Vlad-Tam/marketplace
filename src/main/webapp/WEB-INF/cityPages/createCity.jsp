<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating city</title>
</head>
<body>
    <h1>Creating new city:</h1>
    <form action="/CreateCityServlet" method="post">
        <label for="region">Region:</label><br>
        <input type="text" id="region" name="region"><br>
        <label for="name">City name:</label><br>
        <input type="text" id="name" name="name"><br>
        <input type="submit" value="Create">
    </form>
</body>
</html>
