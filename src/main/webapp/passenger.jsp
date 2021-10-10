<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Passenger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%@include file="components/navbar.jsp"%>
<%
    if( session.getAttribute("auth") == null )
        response.sendRedirect("fourAces?command=Customer");
%>
<%
    if (request.getAttribute("passengers") != null) {
        List<Passenger> passengers = (List<Passenger>) request.getAttribute("passengers");
%>

<h2>All Passengers</h2>
<%
    for (Passenger passenger : passengers) {
%>
<div>passenger_id: <%= passenger.getId()%>
</div>
<div>passenger_firstName: <%= passenger.getFirstName()%>
</div>
<div>passenger_lastName: <%= passenger.getLastName()%>
</div>
<div>identificationType: <%= passenger.getIdType()%>
</div>
<div>identificationNumber: <%= passenger.getIdNumber()%>
</div>

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
