<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
<%
  if( session.getAttribute("auth") == null )
    response.sendRedirect("fourAces?command=Airline");
%>
  <h2>Airline Home Page</h2>
  <h3>You have successfully logged in</h3>
  <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=CreateFlight'">Create Flight</button>
  <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=GetFlight'">View Flight</button>
</body>
</html>