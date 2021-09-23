<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    if (request.getAttribute("flight") != null) {
        Flight flight = (Flight) request.getAttribute("flight");
%>

<h2>All Flights</h2>

<div>ID: <%= flight.getId()%></div>
<div>Flight Code: <%= flight.getCode()%></div>
<div>Date: <%= flight.getDate()%></div>
<div>Time: <%= flight.getTime()%></div>
<div>Source: <%= flight.getSourceAirport().getReferenceCode()%></div>
<div>Destination: <%= flight.getDestinationAirport().getReferenceCode()%></div>
<div>Airline: <%= flight.getAirline().getName()%></div>
<br/>

<%
} else {
%>

<h1>No flight record found.</h1>

<% } %>
</body>
</html>