<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page for Airline and Customer</title>
</head>
<body>
<h1>Login page for Airline and Customer</h1>
<form action="fourAces?command=Login" method="post">
    <label for="type">Login As: </label><select name="type" id="type">
        <option value="customer">Customer</option>
        <option value="airline">Airline Company</option>
    </select>
    <br>
    <label for="email">Email: </label><input type="text" id="email" name="email"><br>
    <label for="password">Password: </label><input type="text" id="password" name="password"><br>
    <input type="submit" value="Login">
</form>
<a href="register.jsp"><button>Register</button></a>
</body>
</html>

