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
<%
    String error = (String) request.getAttribute("error");
%>
<h1>User Registration</h1>
<br>
<form action="fourAces?command=Register" method="post">
    <label for="email">Email:</label><input type="text" id="email" name="email"><br>
    <label for="username">Username:</label><input type="text" id="username" name="username"><br>
    <label for="password">password:</label><input type="text" id="password" name="password"><br>
    <label for="type">Type of account:</label>
    <select name="type" id="type">
        <option value="Customer">Customer</option>
        <option value="Airline">Airline</option>
        <option value="Admin">Admin</option>
    </select>
    <input type="submit" value="Create Account">
</form>
<%
    if( error != null ){
%>
<p style="color: red"><%=error%></p>
<%
    }
%>
<a href="login.jsp"><button>Login</button></a>
</body>
</html>
