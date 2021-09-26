<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.domain.Airline" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
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
                    <th>Airplane</th>
                    <th>Create Tickets</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
        <tbody>
                <%
                    for (Flight flight : flights) {
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
                    <td><%= flight.getSourceAirport().getReferenceCode()%>
                    </td>
                    <td><%= flight.getDestinationAirport().getReferenceCode()%>
                    </td>
                    <td><%= flight.getAirplane().getType()%>
                    </td>
                    <td>
                        <form action="fourAces?command=CreateTicket" method="post">
                            <input type="hidden" name="flightId" value=<%= flight.getId()%>>
                            <input type="hidden" name="airplaneId" value="<%=flight.getAirplaneId()%>">
                            <button type="submit">Create Tickets</button>
                        </form>
                    </td>
                    <td>
                        <form action="fourAces?command=EditFlight">
                            <input type="hidden" name="flightId" value=<%= flight.getId()%>>
                            <input type="hidden" name="code" value=<%=flight.getCode()%>>
                            <input type="hidden" name="date" value=<%=flight.getDate()%>>
                            <input type="hidden" name="time" value=<%=flight.getTime()%>>
                            <button type="submit">Edit Flight</button>
                        </form>
                    </td>
                    <td>
                        <form action="fourAces?command=DeleteFlight" method="post">
                            <input type="hidden" name="flightId" value=<%=flight.getId()%>>
                            <button type="submit">Delete Flight</button>
                        </form>
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
