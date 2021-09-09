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
<br/>
<form action = "login" method = "post">
    Username: <input type = "text" name = "username"><br/>
    Password: <input type = "password" name = "password"><br/>
    <input type = "submit" value = "Login">
</form>
</body>
</html>