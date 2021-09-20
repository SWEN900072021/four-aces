<%@ page import="com.example.four_aces.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h2>Home Page</h2>

<button onclick="window.location.href = 'createFlight.jsp'">Create Flights</button>

<button onclick="window.location.href = '<%= request.getContextPath()%>/frontServlet?command=GetFlights'">View Flights</button>

<form action = "frontServlet?command=SearchFlight" method = "post">
    Date: <input type = "text" name = "flightDate"><br/>
    Time: <input type = "text" name = "flightTime"><br/>
    <input type = "submit" value = "Search Flights">
</form>

</body>
</html>