<%@ page import="com.example.domain.Airport" %>
<%@ page import="com.example.datasource.AirportDataMapper" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Airplane" %>
<%@ page import="com.example.datasource.AirplaneDataMapper" %>
<%@ page import="com.example.domain.Flight" %>
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
        Flight flight = (Flight) request.getAttribute("flight");
    %>
    <h2>Edit Flight</h2>
    <form action = "fourAces?command=EditFlight" method = "post">
        <input type="hidden" name="flightId" value=<%=request.getParameter("flightId")%>>
        <table>
            <tr>
                <td>Flight Code</td>
                <td><input type = "text" name = "flightCode" value = <%=flight.getCode()%>></td>
            </tr>
            <tr>
                <td>Flight Date</td>
                <td><input type = "text" name = "flightDate" value = <%=flight.getDate()%>></td>
            </tr>
            <tr>
                <td>Flight Time</td>
                <td><input type = "text" name = "flightTime" value = <%=flight.getTime()%>></td>
            </tr>
            <tr>
                <td>
                    <label for="source">Source Airport</label>
                </td>
                <td>
                    <select name="source" id="source">
                        <%
                            List<Airport> airports = AirportDataMapper.getInstance().getAll();
                            for(int i = 0; i < airports.size(); i++) {
                                Airport airport = airports.get(i);
                                int airportId = airport.getId();
                                String referenceCode = airport.getReferenceCode();
                                Boolean selected = referenceCode.equals(flight.getSource().getReferenceCode());
                        %>
                        <option <%=selected ? "selected" : ""%> value=<%=airportId%>><%=referenceCode%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="destination">Destination Airport</label>
                </td>
                <td>
                    <select name="destination" id="destination">
                        <%
                            for(int i = 0; i < airports.size(); i++) {
                                Airport airport = airports.get(i);
                                int airportId = airport.getId();
                                String referenceCode = airport.getReferenceCode();
                                Boolean selected = referenceCode.equals(flight.getDestination().getReferenceCode());
                        %>
                        <option <%=selected ? "selected" : ""%> value=<%=airportId%>><%=referenceCode%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="airplane">Airplane</label>
                </td>
                <td>
                    <select name="airplane" id="airplane">
                        <%
                            List<Airplane> airplanes = AirplaneDataMapper.getInstance().getAll();
                            for(int i = 0; i < airplanes.size(); i++) {
                                Airplane airplane = airplanes.get(i);
                                int airplaneId = airplane.getId();
                                String type = airplane.getType();
                                Boolean selected = type.equals(flight.getAirplane().getType());
                        %>
                        <option <%=selected ? "selected" : ""%> value=<%=airplaneId%>><%=type%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <input type = "submit" value = "Save Edit">
    </form>
    <p style="color: red">${error}</p>
    <%
        session.removeAttribute("error");
    %>
</body>
</html>
