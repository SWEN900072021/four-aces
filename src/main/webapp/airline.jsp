<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
  <h2>Airline Home Page</h2>
  <h3>You have successfully logged in</h3>
  <button onclick="window.location.href = 'createFlight.jsp'">Create Flights</button>
  <button onclick="window.location.href = '<%= request.getContextPath()%>/frontServlet?command=GetFlights'">View Flights</button>

</body>
</html>