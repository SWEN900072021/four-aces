<%--
  Created by IntelliJ IDEA.
  User: Sean
  Date: 15/09/2021
  Time: 10:45 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>User Registration</h1>
<br>
<form action="fourAces?command=Register" method="post">
    <label for="email">Email:</label><input type="text" id="email" name="email"><br>
    <label for="username">Username:</label><input type="text" id="username" name="username"><br>
    <label for="password">password:</label><input type="text" id="password" name="password"><br>
    <label for="type">Type of account:</label>
    <select name="type" id="type">
        <option value="customer">customer</option>
        <option value="airline">airline</option>
        <option value="admin">admin</option>
    </select>
    <input type="submit" value="Create Account">
</form>
</body>
</html>
