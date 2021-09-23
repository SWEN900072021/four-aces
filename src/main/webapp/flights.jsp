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

    <div align="left">
        <table border="1" cellpadding="5">
            <caption><h2>List of flight</h2></caption>
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
                    <td><%= flight.getSource()%></td>
                    <td><%= flight.getDestination()%></td>
                    <td>
                        <button onclick="window.location.href = '<%= request.getContextPath()%>/editFlight.jsp?id=<%=flights.get(i).getId()%>&code=<%= flights.get(i).getCode()%>&date=<%=flights.get(i).getDate()%>&time=<%=flights.get(i).getTime()%>'">Edit</button>
                    </td>
                    <td>
                        <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=DeleteFlight&id=<%= flights.get(i).getId()%>'">Delete</button>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <%
    } else {
    %>

    <h2>No flight record found.</h2>

    <% } %>
</body>
</html>
