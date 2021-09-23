<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
  <%
    int airlineId = Integer.parseInt(request.getParameter("id"));
  %>
  <h2>Airline Home Page</h2>
  <h3>You have successfully logged in</h3>
  <button onclick="window.location.href = 'createFlight.jsp?id=<%=airlineId%>'">Create Flight</button>
  <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=GetFlight&id=<%=airlineId%>'">View Flight</button>
</body>
</html>