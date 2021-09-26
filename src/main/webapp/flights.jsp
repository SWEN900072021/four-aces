<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.datasource.TicketDataMapper" %>
<%@ page import="com.example.domain.Ticket" %>
<%@ page import="java.util.ArrayList" %>
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
                    <th>Airplane</th>
                    <th>Create Tickets</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
        <tbody>
                <%
                    TicketDataMapper ticketDataMapper = TicketDataMapper.getInstance();
                    for(int i = 0; i < flights.size(); i++) {
                        Flight flight = flights.get(i);
                        List<Ticket> tickets = new ArrayList<>();
                        try {
                            tickets = ticketDataMapper.getAll(flight.getId());
                        } catch (Exception e) {
                        }
                %>
                <tr>
                    <td><%= flight.getId()%></td>
                    <td><%= flight.getCode()%></td>
                    <td><%= flight.getDate()%></td>
                    <td><%= flight.getTime()%></td>
                    <td><%= flight.getSourceAirport().getReferenceCode()%></td>
                    <td><%= flight.getDestinationAirport().getReferenceCode()%></td>
                    <td><%= flight.getAirplane().getType()%></td>
                    <td>
                        <button <%=tickets.size() > 0 ? "disabled" : ""%> onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=CreateTicket&airlineId=<%=airlineId%>&flightId=<%= flight.getId()%>&airplaneId=<%=flight.getAirplaneId()%>'">Create Tickets</button>
                    </td>
                    <td>
                        <button onclick="window.location.href ='<%= request.getContextPath()%>/editFlight.jsp?airlineId=<%=airlineId%>&flightId=<%=flight.getId()%>&code=<%= flight.getCode()%>&date=<%=flight.getDate()%>&time=<%=flight.getTime()%>&source=<%= flight.getSourceAirport().getReferenceCode()%>&destination=<%= flight.getDestinationAirport().getReferenceCode()%>&airplane=<%= flight.getAirplane().getType()%>'">Edit</button>
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
    <br/>
    <button onclick="window.location.href = 'airline.jsp?airlineId=<%=airlineId%>'">Return to Home Page</button>
</body>
</html>
