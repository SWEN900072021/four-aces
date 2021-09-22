<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="java.util.List" %>
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
<div>Date: <%= flights.get(i).getDate()%></div>
<div>Time: <%= flights.get(i).getTime()%></div>
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