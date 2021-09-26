<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Passenger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
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
<div>passenegr_firstName: <%= passenger.getfirstName()%>
</div>
<div>passenger_lastName: <%= passenger.getlastName()%>
</div>
<div>identificationType: <%= passenger.getIdentificationType()%>
</div>
<div>identificationNumber: <%= passenger.getIdentificationNumber()%>
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
