<%@ page import="java.util.List" %>
<%@ page import="main.java.com.example.four_aces.domain.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    if (request.getAttribute("flights") != null) {
        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
%>

<h2>All Flights</h2>
<%
    for(int i = 0; i < flights.size(); i++) {
%>
    <div>ID: <%= flights.get(i).getId()%></div>
    <div>Flight Code: <%= flights.get(i).getFlightCode()%></div>
    <div>Date: <%= flights.get(i).getFlightDate()%></div>
    <div>Time: <%= flights.get(i).getFlightTime()%></div>
    <br/>
<%
    }
%>

<%
} else {
%>

<h1>No flight record found.</h1>

<% } %>
</body>
</html>
