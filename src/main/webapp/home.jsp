<%@ page import="com.example.four_aces.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    if (request.getAttribute("customer") != null) {
        Customer customer = (Customer) request.getAttribute("customer");
%>

<h2>All Passengers</h2>
<div>ID: <%= customer.getId()%></div>
<div>Username: <%= customer.getUsername()%></div>
<div>Password: <%= customer.getPassword()%></div>

<%
} else {
%>

<h1>No customer record found.</h1>

<% } %>
</body>
</html>