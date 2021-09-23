<%@ page import="com.example.datasource.AirportDataMapper" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
    <%
        int airlineId = Integer.parseInt(request.getParameter("airlineId"));
    %>
    <h2>Create Flight</h2>
    <form action = "fourAces?command=CreateFlight&airlineId=<%=airlineId%>" method = "post">
        <table>
            <tr>
                <td>Flight Code</td>
                <td><input type = "text" name = "flightCode"></td>
            </tr>
            <tr>
                <td>Flight Date</td>
                <td><input type = "text" name = "flightDate"></td>
            </tr>
            <tr>
                <td>Flight Time</td>
                <td><input type = "text" name = "flightTime"></td>
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
                        %>
                        <option value=<%=airportId%>><%=referenceCode%></option>
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
                        %>
                        <option value=<%=airportId%>><%=referenceCode%></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <input type = "submit" value = "Create Flight">
    </form>
</body>
</html>
