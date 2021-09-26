<%@ page import="com.example.domain.Airline" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
  <h2>Airline Home Page</h2>
  <h3>You have successfully logged in</h3>
  <button onclick="window.location.href = 'createFlight.jsp'">Create Flight</button>
  <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=GetFlight'">View Flight</button>
</body>
</html>