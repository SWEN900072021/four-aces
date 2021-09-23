<%@ page import="com.example.datasource.AirportDataMapper" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h2>Create Flight</h2>
<form action = "fourAces?command=CreateFlight" method = "post">
    Flight code: <input type = "text" name = "flightCode"><br/>
    Flight date: <input type = "text" name = "flightDate"><br/>
    Flight time: <input type = "text" name = "flightTime"><br/>

    <label for="source">Source Airport: </label>
    <select name="source" id="source">
        <%
            List<Airport> airports = AirportDataMapper.getInstance().getAll();
            for(int i = 0; i < airports.size(); i++) {
                Airport airport = airports.get(i);
                int airportId = airport.getId();
        %>
        <option value=<%=airportId%>><%=airportId%></option>
        <%
            }
        %>
    </select><br/>

    <label for="destination">Destination Airport: </label>
    <select name="destination" id="destination">
        <%
            for(int i = 0; i < airports.size(); i++) {
                Airport airport = airports.get(i);
                int airportId = airport.getId();
        %>
        <option value=<%=airportId%>><%=airportId%></option>
        <%
            }
        %>
    </select><br/>

    <input type = "submit" value = "Create Flight">
</form>
</body>
</html>
