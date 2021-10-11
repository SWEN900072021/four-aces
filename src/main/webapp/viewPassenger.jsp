<%@ page import="com.example.domain.Passenger" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: yiyua
  Date: 6/10/2021
  Time: 9:29 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Passenger</title>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<%
    if (session.getAttribute("auth") == null)
        response.sendRedirect("fourAces?command=Airline");
%>
<%
    ArrayList<Passenger> passengers;
    if ( (passengers = (ArrayList<Passenger>) request.getAttribute("passengers")) == null)
        response.sendRedirect("fourAces?command=Airline");
    else {
        %>
<table border="1" cellpadding="5">
    <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>ID type</th>
        <th>ID number</th>
    </tr>
<%
        for (Passenger passenger : passengers){
%>
    <tr>
        <td><%=passenger.getFirstName()%></td>
        <td><%=passenger.getLastName()%></td>
        <td><%=passenger.getIdType()%></td>
        <td><%=passenger.getIdNumber()%></td>
    </tr>
<%
        }
%>
</table>
        <%
    }
%>
</body>
</html>
