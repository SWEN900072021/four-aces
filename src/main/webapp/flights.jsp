<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.domain.Ticket" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
<%@include file="components/navbar.jsp"%>
<%
    if( session.getAttribute("auth") == null )
        response.sendRedirect("fourAces?command=Airline");
%>
    <%
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
                    <th>Stopovers</th>
                    <th>Airplane</th>
                    <th>Create Tickets</th>
                    <th>Edit</th>
                    <th>Delete</th>
                    <th>View Passenger</th>
                    <th>View Customer</th>
                </tr>
            </thead>
        <tbody>
                <%
                    for (Flight flight : flights) {
                        List<Ticket> tickets = flight.getAvailableTickets();
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
                    <td><%= flight.getSource().getAddress()%>
                    </td>
                    <td><%= flight.getDestination().getAddress()%>
                    </td>
                    <td><%= flight.getStopoverAirportsString()%>
                    </td>
                    <td><%= flight.getAirplane().getType()%>
                    </td>
                    <td>
                        <form action="fourAces?command=CreateTicket" method="post">
                            <input type="hidden" name="flightId" value=<%= flight.getId()%>>
                            <input type="hidden" name="airplaneId" value="<%=flight.getAirplane().getId()%>">
                            <button <%=tickets.size() > 0 ? "disabled" : ""%> type="submit">Create Tickets</button>
                        </form>
                    </td>
                    <td>
                        <form action="fourAces?command=EditFlight" method="post">
                            <input type="hidden" name="forward" value="forward">
                            <input type="hidden" name="flightId" value=<%= flight.getId()%>>
                            <button type="submit">Edit Flight</button>
                        </form>
                    </td>
                    <td>
                        <form action="fourAces?command=DeleteFlight" method="post">
                            <input type="hidden" name="flightId" value=<%=flight.getId()%>>
                            <button type="submit">Delete Flight</button>
                        </form>
                    </td>
                    <td>
                        <form action="fourAces?command=ViewPassenger" method="post">
                            <input type="hidden" name="flightId" value=<%=flight.getId()%>>
                            <button type="submit">View Passenger</button>
                        </form>
                    </td>
                    <td>
                        <form action="fourAces?command=ViewCustomer" method="post">
                            <input type="hidden" name="flightId" value=<%=flight.getId()%>>
                            <button type="submit">View Customer</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    <br/>
    <button onclick="window.location.href = '<%= request.getContextPath()%>/airline.jsp'">Return to Home Page</button>
    <p style="color: red">${error}</p>
    <%
        session.removeAttribute("error");
    %>
</body>
</html>
