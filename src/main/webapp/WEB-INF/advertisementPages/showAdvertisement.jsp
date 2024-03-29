<%@ page import="com.vladtam.jspapplication.models.Advertisement" %>
<%@ page import="com.vladtam.jspapplication.models.User" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 28.03.2024
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advertisement</title>
</head>
<body>
    <h1>Advertisement information:</h1>
    <% Advertisement advertisement = (Advertisement) request.getAttribute("advertisementInfo"); %>
    <h4>ID: <%= advertisement.getId() %></h4>
    <h4>Name: <%= advertisement.getBasicInfo().getName() %></h4>
    <h4>Description: <%= advertisement.getBasicInfo().getDescription() %></h4>
    <h4>Price: <%= advertisement.getBasicInfo().getPrice() %></h4>
    <h4>Category: <%= advertisement.getCategory().getName() %></h4>
    <h4>Category description: <%= advertisement.getCategory().getDescription() %></h4>
    <h4>Seller: <%= advertisement.getVendor().getBasicInfo().getName() + " " + advertisement.getVendor().getBasicInfo().getSurname() %></h4>
    <h4>Wishing people:</h4>
    <% for (User user : advertisement.getWishingPeopleList()) { %>
    <li style="display: flex; align-items: center;">
        <a href="<%= "../../users/" + user.getId() %>" style="margin-right: 10px;">
            <%= "   " + user.getBasicInfo().getSurname() + " " + user.getBasicInfo().getName() %>
        </a>
        <form action="/DeleteWishServlet" method="post" style="margin-bottom: 0;">
            <input type="hidden" name="userId" value="<%=user.getId()%>" />
            <input type="hidden" name="advertisementId" value="<%=advertisement.getId()%>" />
            <input type="hidden" name="originalPage" value="<%="/advertisements/" + advertisement.getId()%>" />
            <input type="submit" value="Delete" />
        </form>
    </li>
    <% } %>
    <form action="/GetCreatingWishesFieldsServlet" method="post">
        <input type="hidden" name="advertisementId" value="<%=advertisement.getId()%>" />
        <input type="hidden" name="originalPage" value="<%="/advertisements/" + advertisement.getId()%>" />
        <input type="submit" value="Add to wish list" />
    </form>
    <h3>
        <a href="../advertisements">Return to advertisements list</a>
        <form action="/DeleteAdvertisementServlet" method="post">
            <input type="hidden" name="advertisementId" value="<%=advertisement.getId()%>" />
            <input type="submit" value="Delete this advertisement" />
        </form>
    </h3>
</body>
</html>
