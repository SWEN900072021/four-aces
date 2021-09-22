<%@ page import="java.util.List" %>
<%@ page import="com.example.four_aces.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    if (request.getAttribute("customers") != null) {
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>

<h2>All Customers</h2>
<%
    for(int i = 0; i < customers.size(); i++) {
%>
<div>ID: <%= customers.get(i).getId()%></div>
<div>Username: <%= customers.get(i).getUsername()%></div>
<div>Password: <%= customers.get(i).getPassword()%></div>
<br/>
<%
    }
%>

<%
} else {
%>

<h1>No customer record found.</h1>

<% } %>
</body>
</html>
