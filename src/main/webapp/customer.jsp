<%@ page import="com.example.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
  <h2>Customer Home Page</h2>
  <h3>You have successfully logged in</h3>

  <%
    if (request.getParameter("customerId") != null) {
      int customerId = Integer.parseInt(request.getParameter("customerId"));
  %>


  <form action="fourAces?command=SearchFlights&customerId=<%= customerId%>" method = "post">
    <label for="date">Date: </label><input type="text" id="date" name="date"><br>
    <label for="origin">Origin: </label><input type="text" id="origin" name="origin"><br>
    <label for="destination">Destination: </label><input type="text" id="destination" name="destination"><br>
    <input type="submit" value="Search Flights">
  </form>

  <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=UpcomingFlights&customerId=<%=customerId%>'">View Upcoming Flights</button>
  <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=PreviousFlights&customerId=<%=customerId%>'">View Previous Flights</button>
  <%
    }
  %>
  <%
    String error = (String) request.getAttribute("error");
    if( request.getAttribute("error") != null ){
  %>
  <p style="color: red"><%=error%></p>
  <%
    }
  %>
</body>
</html>
