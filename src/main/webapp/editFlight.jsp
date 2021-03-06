<%@ page import="com.example.domain.Airport" %>
<%@ page import="com.example.datasource.AirportDataMapper" %>
<%@ page import="java.util.List" %>
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
        List<Airport> airports = AirportDataMapper.getInstance().getAll();
        List<Airport> stopovers = flight.getStopoverAirports();
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
                    <label for="stopover1">Stopover 1</label>
                </td>
                <td>
                    <select name="stopover1" id="stopover1">
                        <option value=""></option>
                        <%
                            for(int i = 0; i < airports.size(); i++) {
                                Airport airport = airports.get(i);
                                int airportId = airport.getId();
                                String referenceCode = airport.getReferenceCode();
                                Boolean selected = false;
                                if (stopovers.size() > 0) {
                                    selected = referenceCode.equals(stopovers.get(0).getReferenceCode());
                                }
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
                    <label for="stopover2">Stopover 2</label>
                </td>
                <td>
                    <select name="stopover2" id="stopover2">
                        <option value=""></option>
                        <%
                            for(int i = 0; i < airports.size(); i++) {
                                Airport airport = airports.get(i);
                                int airportId = airport.getId();
                                String referenceCode = airport.getReferenceCode();
                                Boolean selected = false;
                                if (stopovers.size() > 1) {
                                    selected = referenceCode.equals(stopovers.get(1).getReferenceCode());
                                }
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
                    <label for="stopover3">Stopover 3</label>
                </td>
                <td>
                    <select name="stopover3" id="stopover3">
                        <option value=""></option>
                        <%
                            for(int i = 0; i < airports.size(); i++) {
                                Airport airport = airports.get(i);
                                int airportId = airport.getId();
                                String referenceCode = airport.getReferenceCode();
                                Boolean selected = false;
                                if (stopovers.size() > 2) {
                                    selected = referenceCode.equals(stopovers.get(2).getReferenceCode());
                                }
                        %>
                        <option <%=selected ? "selected" : ""%> value=<%=airportId%>><%=referenceCode%></option>
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
