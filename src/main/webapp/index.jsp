<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Travel Reservation System" %>
</h1>
<h2><%= "Four Aces" %></h2>
<br/>
<form action = "testlogin" method = "post">
    User name: <input type = "text" name = "userName"><br/>
    Password: <input type = "password" name = "passWord"><br/>
    <input type = "submit" value = "Login">
</form>
</body>
</html>