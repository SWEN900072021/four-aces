<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%@include file="components/admin-header.jsp"%>
<%
    if (request.getAttribute("airports") != null) {
        List<Airport> airports = (List<Airport>) request.getAttribute("airports");
%>

<h2>All Airports</h2>
<%
    for (Airport airport : airports) {
%>
<div>referenceCode: <%= airport.getReferenceCode()%>
</div>
<div>address: <%= airport.getAddress()%>
</div>
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
