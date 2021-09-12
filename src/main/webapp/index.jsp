<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h1><%= "Travel Reservation System" %>
</h1>
<h2><%= "Four Aces" %></h2>
<h3>Login</h3>
<form action = "frontServlet?command=Customer" method = "post">
    Username: <input type = "text" name = "username"><br/>
    Password: <input type = "password" name = "password"><br/>
    <input type = "submit" value = "Login">
</form>
</body>
</html>