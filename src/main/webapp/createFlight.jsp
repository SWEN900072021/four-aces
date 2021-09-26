<%@ page import="com.example.datasource.AirportDataMapper" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.datasource.AirplaneDataMapper" %>
<%@ page import="com.example.domain.Airplane" %>
<%@ page import="com.example.domain.Airline" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS | Create Flight</title>
</head>
<body>
    <%
        List<Airport> airports = AirportDataMapper.getInstance().getAll();
        if (airports.size() > 0) {
    %>
            <h2>Create Flight</h2>
            <form action = "fourAces?command=CreateFlight" method = "post">
                <table>
                    <tr>
                        <td>Flight Code</td>
                        <td><input type = "text" name = "flightCode" placeholder="JQ569"></td>
                    </tr>
                    <tr>
                        <td>Flight Date</td>
                        <td><input type = "text" name = "flightDate" placeholder="2021/09/27"></td>
                    </tr>
                    <tr>
                        <td>Flight Time</td>
                        <td><input type = "text" name = "flightTime" placeholder="21:45"></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="source">Source Airport</label>
                        </td>
                        <td>
                            <select name="source" id="source">
                                <%
                                    for (Airport airport : airports) {
                                        int airportId = airport.getId();
                                        String referenceCode = airport.getReferenceCode();
                                %>
                                <option value=<%=airportId%>><%=referenceCode%>
                                </option>
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
                                    for (Airport airport : airports) {
                                        int airportId = airport.getId();
                                        String referenceCode = airport.getReferenceCode();
                                %>
                                <option value=<%=airportId%>><%=referenceCode%>
                                </option>
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
                                    for (Airplane airplane : airplanes) {
                                        int airplaneId = airplane.getId();
                                        String type = airplane.getType();
                                %>
                                <option value=<%=airplaneId%>><%=type%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type = "submit" value = "Create Flight">
            </form>
    <%
        } else {
    %>
            <p>Sorry, no airports available.</p>
    <%
        }
    %>
</body>
</html>
