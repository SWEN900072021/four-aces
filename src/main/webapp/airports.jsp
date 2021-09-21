<%@ page import="java.util.List" %>
<%@ page import="com.example.four_aces.domain.Airport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    if (request.getAttribute("airports") != null) {
        List<Airport> airports = (List<Airport>) request.getAttribute("airports");
%>

<h2>All Flights</h2>
<%
    for(int i = 0; i < airports.size(); i++) {
%>
    <div>referenceCode: <%= airports.get(i).getReferenceCode()%></div>
    <div>address: <%= airports.get(i).getAddress()%></div>
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
