<%@ page import="java.util.List" %>
<%@ page import="com.example.four_aces.domain.Passenger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    if (request.getAttribute("passengers") != null) {
        List<Passenger> passengers = (List<Passenger>) request.getAttribute("passengers");
%>

<h2>All Passengers</h2>
<%
    for(int i = 0; i < passengers.size(); i++) {
%>
<div>passenger_id: <%= passengers.get(i).getPassenger_id()%></div>
<div>passenegr_firstName: <%= passengers.get(i).getPassenger_firstName()%></div>
<div>passenger_lastName: <%= passengers.get(i).getPassenger_lastName()%></div>
<div>identificationType: <%= passengers.get(i).getIdentificationType()%></div>
<div>identificationNumber: <%= passengers.get(i).getIdentificationNumber()%></div>

<br/>
<%
    }
%>

<%
} else {
%>

<h1>No passenger record found.</h1>

<% } %>
</body>
</html>
