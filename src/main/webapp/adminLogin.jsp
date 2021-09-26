<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
<h1>Admin Login</h1>
<form action="fourAces?command=AdminLogin" method="post">
    <label for="username">Username: </label><input type="text" id="username" name="username"><br>
    <label for="password">Password: </label><input type="password" id="password" name="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
