<%@ page import="com.vladtam.jspapplication.models.Wish" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 29.03.2024
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Wishes</title>
</head>
<body>
  <h1>Wishes:</h1>
  <ul>
      <% for (Wish wish : (List<Wish>) request.getAttribute("wishes")) { %>
      <li>
          <%= wish.getUser().getBasicInfo().getSurname() + " " + wish.getUser().getBasicInfo().getName() + " <-> " + wish.getAdvertisement().getBasicInfo().getName() + ", price: " + wish.getAdvertisement().getBasicInfo().getPrice() + "$" %>
      </li>
      <% } %>
  </ul>
  <h3>
      <form action="/GetCreatingWishesFieldsServlet" method="post">
          <input type="hidden" name="originalPage" value="<%="/wishes"%>" />
          <input type="submit" value="Add new wish" />
      </form>
      <a href="../">Return to main menu</a>
  </h3>
</body>
</html>
