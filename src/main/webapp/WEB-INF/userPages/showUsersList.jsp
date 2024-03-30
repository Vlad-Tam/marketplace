<%@ page import="com.vladtam.jspapplication.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Users</title>
</head>
<body>
<h1>Choice user:</h1>
<ul>
    <% for (User user : (List<User>) request.getAttribute("users")) { %>
    <li>
        <a href="<%= "users/" + user.getId() %>">
            <%= user.getBasicInfo().getSurname() + " " + user.getBasicInfo().getName() %>
        </a>
    </li>
    <% } %>
</ul>
<h3>
    <a href="/users/new">Create new user</a></br>
    <a href="../">Return to main menu</a>
</h3>
</body>
</html>
