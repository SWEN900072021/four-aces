<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
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
        Flight flight = flights.get(i);
%>
    <div>ID: <%= flight.getId()%></div>
    <div>Flight Code: <%= flight.getCode()%></div>
    <div>Date: <%= flight.getDate()%></div>
    <div>Time: <%= flight.getTime()%></div>
    <div>Source: <%= flight.getSource()%></div>
    <div>Destination: <%= flight.getDestination()%></div>
    <button onclick="window.location.href = '<%= request.getContextPath()%>/editFlight.jsp?id=<%=flights.get(i).getId()%>&code=<%= flights.get(i).getCode()%>&date=<%=flights.get(i).getDate()%>&time=<%=flights.get(i).getTime()%>'">Edit</button>
    <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=DeleteFlight&id=<%= flights.get(i).getId()%>'">Delete</button>
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
