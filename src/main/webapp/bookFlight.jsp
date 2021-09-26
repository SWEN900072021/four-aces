<%@ page import="com.example.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    if( session.getAttribute("auth") == null )
        response.sendRedirect("fourAces?command=Customer");
%>
<form action = "${pageContext.request.contextPath}/fourAces?command=BookFlight" method = "post">
    Date: <input type = "date" name = "flightDate"><br/>
    Time: <input type = "time" name = "flightTime"><br/>
    <input type = "submit" value = "Search Flights">
</form>
</body>
</html>