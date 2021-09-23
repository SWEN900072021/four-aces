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
%>
    <div>ID: <%= flights.get(i).getId()%></div>
    <div>Flight Code: <%= flights.get(i).getCode()%></div>
    <div>Date: <%= flights.get(i).getDate()%></div>
    <div>Time: <%= flights.get(i).getTime()%></div>
    <div>Source: <%= flights.get(i).getSource()%></div>
    <div>Destination: <%= flights.get(i).getDestination()%></div>
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
