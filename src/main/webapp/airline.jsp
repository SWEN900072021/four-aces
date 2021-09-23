<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
  <%
    int airlineId = Integer.parseInt(request.getParameter("airlineId"));
  %>
  <h2>Airline Home Page</h2>
  <h3>You have successfully logged in</h3>
  <button onclick="window.location.href = 'createFlight.jsp?airlineId=<%=airlineId%>'">Create Flight</button>
  <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=GetFlight&airlineId=<%=airlineId%>'">View Flight</button>
</body>
</html>