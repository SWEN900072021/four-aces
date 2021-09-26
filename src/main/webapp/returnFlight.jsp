<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>

<h2>Would you like a return flight?</h2>
<%
    List<Flight> flights = (List<Flight>) request.getAttribute("returnFlights");
    int customerId = Integer.parseInt(request.getParameter("customerId"));
%>

<button onclick="window.location.href = '<%= request.getContextPath()%>/addPassenger.jsp?customerId=<%=customerId%>'">One way only</button>

<div align="left">
    <table border="1" cellpadding="5">
        <caption><h2>List of return flights</h2></caption>
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
                <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=ViewFlight&type=return&customerId=<%= customerId%>&flightId=<%= flight.getId()%>'">View</button>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
