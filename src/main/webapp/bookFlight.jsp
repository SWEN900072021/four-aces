<%@ page import="com.example.domain.Customer" %>
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

<form action = "frontServlet?command=BookFlight" method = "post">
    Date: <input type = "text" name = "flightDate"><br/>
    Time: <input type = "text" name = "flightTime"><br/>
    <input type = "submit" value = "Search Flights">
</form>
<%
    }
%>
</body>
</html>