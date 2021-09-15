<%@ page import="main.java.com.example.four_aces.domain.Customer" %>
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

<h2>Home Page</h2>
<div>ID: <%= customer.getId()%></div>
<div>Username: <%= customer.getUsername()%></div>
<div>Password: <%= customer.getPassword()%></div>

<form action = "frontServlet?command=FlightCreationForm" method = "post">
    <input type = "submit" value = "Create Flights">
</form>

<form action = "frontServlet?command=GetCustomers" method = "post">
    <input type = "submit" value = "Get Customers">
</form>
<%
} else {
%>

<h1>No customer record found.</h1>

<% } %>
</body>
</html>