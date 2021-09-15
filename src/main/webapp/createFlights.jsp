<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h2>Create Flight</h2>
<form action = "frontServlet?command=CreateFlights" method = "post">
    Flight code: <input type = "text" name = "flightCode"><br/>
    Flight date: <input type = "text" name = "FlightDate"><br/>
    Flight time: <input type = "text" name = "FlightTime"><br/>
    <input type = "submit" value = "Create Flight">
</form>
</body>
</html>
