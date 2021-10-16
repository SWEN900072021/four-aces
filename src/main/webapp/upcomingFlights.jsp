<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page import="com.example.domain.Ticket" %>
<%@ page import="java.util.HashMap" %>
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
    HashMap<Flight, List<Ticket>> flights = (HashMap<Flight, List<Ticket>>) request.getAttribute("flights");
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
            <th>Tickets</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Flight flight : flights.keySet()) {
        %>
        <tr>
            <td><%= flight.getId()%>
            </td>
            <td><%= flight.getCode()%>
            </td>
            <td><%= flight.getDate()%>
            </td>
            <td><%= flight.getTime()%>
            </td>
            <td><%= flight.getSource().getReferenceCode()%>
            </td>
            <td><%= flight.getDestination().getReferenceCode()%>
            </td>
            <td>
                <%
                    int i = 0;
                    for (Ticket ticket : flights.get(flight)) {
                        i = i + 1;
                %>
                <h3>Passenger <%=i%></h3>
                <div>First Name: <%= ticket.getPassenger().getFirstName()%></div>
                <div>Last Name: <%= ticket.getPassenger().getLastName()%></div>
                <div>ID Type: <%= ticket.getPassenger().getIdType()%></div>
                <div>ID Number: <%= ticket.getPassenger().getIdNumber()%></div>
                <div>Seat Number: <%= ticket.getSeatNumber()%></div>
                <div>Seat Class: <%= ticket.getSeatClass()%></div>
                <%
                    }
                %>
            </td>

        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <button onclick="window.location.href = '<%= request.getContextPath()%>/customer.jsp?command=Customer'">Back to Homepage</button>
</div>
</body>
</html>
