<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
    <%
        int airlineId = Integer.parseInt(request.getParameter("airlineId"));
        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
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
                    <th>Edit</th>
                    <th>Delete</th>
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
                        <button onclick="window.location.href = '<%= request.getContextPath()%>/editFlight.jsp?airlineId=<%=airlineId%>&flightId=<%=flight.getId()%>&code=<%= flight.getCode()%>&date=<%=flight.getDate()%>&time=<%=flight.getTime()%>'">Edit</button>
                    </td>
                    <td>
                        <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=DeleteFlight&airlineId=<%=airlineId%>&flightId=<%= flight.getId()%>'">Delete</button>
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
