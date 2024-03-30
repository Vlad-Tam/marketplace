<%@ page import="com.vladtam.jspapplication.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vladtam.jspapplication.models.Advertisement" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 29.03.2024
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Creating wish</title>
</head>
<body>
<h1>Creating new wish:</h1>
<form action="/CreateWishServlet" method="post">
  <h3>
    <label for="user">User:</label><br>
  </h3>
  <h4>
    <% User selectedUser = (User) request.getAttribute("user"); %>
    <% if (selectedUser == null) { %>
    <select id="user" name="userId">
      <% for (User user : (List<User>) request.getAttribute("users")) { %>
      <option value="<%= user.getId() %>"><%= user.getBasicInfo().getSurname() + " " + user.getBasicInfo().getName()%></option>
      <% } %>
    </select><br>
    <% } else { %>
    <input type="hidden" id="user" name="userId" value="<%= selectedUser.getId() %>" />
    <p><%= selectedUser.getBasicInfo().getSurname() + " " + selectedUser.getBasicInfo().getName() %></p>
    <% } %>
  </h4>

  <br><br>

  <h3>
    <label for="advertisement">Advertisement:</label><br>
  </h3>
  <h4>
    <% Advertisement selectedAdvertisement = (Advertisement) request.getAttribute("advertisement"); %>
    <% if (selectedAdvertisement == null) { %>
    <select id="advertisement" name="advertisementId">
      <% for (Advertisement advertisement : (List<Advertisement>) request.getAttribute("advertisements")) { %>
      <option value="<%= advertisement.getId() %>"><%= advertisement.getBasicInfo().getName() + ", price: " + advertisement.getBasicInfo().getPrice() + "$"%></option>
      <% } %>
    </select><br>
    <% } else { %>
    <input type="hidden" id="advertisement" name="advertisementId" value="<%= selectedAdvertisement.getId() %>" />
    <p><%= selectedAdvertisement.getBasicInfo().getName() + ", price: " + selectedAdvertisement.getBasicInfo().getPrice() + "$" %></p>
    <% } %>
  </h4>
  <input type="hidden" name="originalPage" value="<%=request.getAttribute("originalPage")%>" />
  <input type="submit" value="Create">
</form>
</body>
</html>
