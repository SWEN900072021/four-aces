<%@ page import="com.example.domain.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>TRS</title>
</head>
<body>
    <h1><%= "Travel Reservation System" %></h1>
    <h2><%= "Four Aces" %></h2>
    <button onclick="window.location.href = 'login.jsp'">Login</button>
    <button onclick="window.location.href = 'register.jsp'">Register</button>
    <p>URL: <%=System.getenv("JDBC_DATABASE_URL")%></p>
</body>
</html>