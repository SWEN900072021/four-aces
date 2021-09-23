<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Airport" %>
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

<h2>All Airports</h2>
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

<h1>No airport record found.</h1>

<% } %>
</body>
</html>
