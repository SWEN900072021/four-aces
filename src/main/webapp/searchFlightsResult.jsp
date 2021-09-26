<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    List<Flight> flights = (List<Flight>) request.getAttribute("flights");
    int customerId = Integer.parseInt(request.getParameter("customerId"));
    if (flights.size() == 0) {
%>
    <h2>No flight found</h2>
<%
    } else {
%>

<div align="left">
    <table border="1" cellpadding="5">
        <caption><h2>List of flights</h2></caption>
        <thead>
        <tr>
            <th>Flight ID</th>
            <th>Flight Code</th>
            <th>Flight Date</th>
            <th>Flight Time</th>
            <th>Source Airport</th>
            <th>Destination Airport</th>
            <th>View</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(int i = 0; i < flights.size(); i++) {
                Flight flight = flights.get(i);
        %>
        <tr>
            <td><%= flight.getId()%></td>
            <td><%= flight.getCode()%></td>
            <td><%= flight.getDate()%></td>
            <td><%= flight.getTime()%></td>
            <td><%= flight.getSourceAirport().getReferenceCode()%></td>
            <td><%= flight.getDestinationAirport().getReferenceCode()%></td>
            <td>
                <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=ViewFlight&type=go&customerId=<%= customerId%>&flightId=<%= flight.getId()%>'">View</button>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
<%
    }
%>
</body>
</html>
