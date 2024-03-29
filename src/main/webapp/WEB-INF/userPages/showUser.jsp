<%@ page import="com.vladtam.jspapplication.models.User" %>
<%@ page import="com.vladtam.jspapplication.models.Advertisement" %>
<%@ page import="com.vladtam.jspapplication.models.Review" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.03.2024
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <h1>User information:</h1>
    <% User user = (User) request.getAttribute("userInfo"); %>
    <h4>ID: <%= user.getId() %></h4>
    <h4>Name: <%= user.getBasicInfo().getName() %></h4>
    <h4>Surname: <%= user.getBasicInfo().getSurname() %></h4>
    <h4>Phone number: <%= user.getBasicInfo().getPhoneNumber() %></h4>
    <h4>Email: <%= user.getBasicInfo().getEmail() %></h4>
    <h4>Password: <%= user.getBasicInfo().getPassword() %></h4>
    <h4>Address: <%= user.getAddress().getCity().getRegion() + " region, city: " + user.getAddress().getCity().getName() +
            ", street " + user.getAddress().getStreet() + ", " + user.getAddress().getHouseNumber() + "-" + user.getAddress().getFlatNumber() %></h4>
    <h4>Products on sale:</h4>
    <% for (Advertisement product : user.getSalesList()) { %>
    <li>
        <%= "   " + product.getBasicInfo().getName() + ", price: " + product.getBasicInfo().getPrice() + "$" %>
    </li>
    <% } %>
    <h4>Wishes:</h4>
    <% for (Advertisement wish : user.getWishList()) { %>
    <li>
        <a href="<%= "../../advertisements/" + wish.getId() %>">
            <%= "   " + wish.getBasicInfo().getName() + ", price: " + wish.getBasicInfo().getPrice() + "$" %>
        </a>
    </li>
    <% } %>
    <h4>Comments:</h4>
    <% for (Review review : user.getCommentsList()) { %>
    <li>
        <%= "   From: " + review.getSender().getBasicInfo().getName() + " " + review.getSender().getBasicInfo().getSurname() + ", rate: " + review.getRate() %>
    </li>
    <% } %>
    <h3>
        <a href="../users">Return to users list</a>
        <form action="/DeleteUserServlet" method="post">
            <input type="hidden" name="userId" value="<%=user.getId()%>" />
            <input type="submit" value="Delete this user" />
        </form>
    </h3>
</body>
</html>
