<%@ page import="main.java.com.example.four_aces.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h2>Home Page</h2>

<button onclick="window.location.href = '<%= request.getContextPath()%>/createFlights.jsp'">Create Flights</button>

<button onclick="window.location.href = '<%= request.getContextPath()%>/frontServlet?command=GetFlights'">View Flights</button>

</body>
</html>