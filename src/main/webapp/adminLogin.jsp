<%--
  Created by IntelliJ IDEA.
  User: yiyua
  Date: 20/09/2021
  Time: 1:57 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
<h1>Admin Login</h1>
<form action="fourAces?command=Login" method="post">
    <input type="hidden" name="type" value="admin">
    <label for="username">Username: </label><input type="text" id="username" name="username"><br>
    <label for="password">Password: </label><input type="text" id="password" name="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
