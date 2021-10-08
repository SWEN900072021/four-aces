<%@ page import="com.example.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
<%@include file="components/navbar.jsp"%>
<%
  if( session.getAttribute("auth") == null )
    response.sendRedirect("fourAces?command=Customer");
%>
  <h2>Customer Home Page</h2>
  <h3>You have successfully logged in</h3>

  <form action="fourAces?command=SearchFlights" method = "post">
    <label for="date">Date: </label><input type="date" id="date" name="date"><br>
    <label for="origin">Origin: </label><input type="text" id="origin" name="origin"><br>
    <label for="destination">Destination: </label><input type="text" id="destination" name="destination"><br>

    <input type="submit" value="Search Flights">
  </form>

  <a href="<%= request.getContextPath()%>/fourAces?command=UpcomingFlights"><button>View Upcoming Flights</button></a>
  <a href="<%= request.getContextPath()%>/fourAces?command=PreviousFlights"><button>View Previous Flights</button></a>

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
