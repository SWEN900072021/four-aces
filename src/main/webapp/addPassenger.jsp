<%@ page import="com.example.domain.Passenger" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.domain.BookingUnitOfWork" %><%--
  Created by IntelliJ IDEA.
  User: tienhinh
  Date: 24/9/21
  Time: 5:33 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter passenger detail</title>
</head>
<body>
<%@include file="components/navbar.jsp" %>

<h2>Enter Passenger Detail</h2>
<%
    ArrayList<Passenger> passengers;
    if ((passengers = ((BookingUnitOfWork) session.getAttribute("bookingUnitOfWork")).getPassengers()) != null) {
%>
<table border="1" cellpadding="5">
    <tr>
        <th>First Name</th>
        <td>Last Name</td>
        <td>Identification Type</td>
        <td>Identification Number</td>
    </tr>
    <%
        for (Passenger passenger : passengers) {
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
<br>
<form action="fourAces?command=AddPassenger" method="post">
    <label for="firstName">First Name: </label><input type="text" id="firstName" name="firstName" required><br>
    <label for="lastName">Last Name: </label><input type="text" id="lastName" name="lastName" required><br>
    <label for="idType">Identification Type: </label><input type="text" id="idType" name="idType" required><br>
    <label for="idNum">Identification Number: </label><input type="text" id="idNum" name="idNum" required><br>
    <input type="submit" value="Add Passenger">
</form>

<a href="<%= request.getContextPath()%>/fourAces?command=SubmitBooking">
    <button>Submit booking</button>
</a>
<a href="<%= request.getContextPath()%>/fourAces?command=CancelBooking">
    <button>Cancel</button>
</a>
<%
    String error = (String) session.getAttribute("error");
    if ( error == null ){
        error = (String) request.getAttribute("error");
    }
    if( error != null ){
%>
<p style="color: red"><%=error%></p>
<%
    }
%>
</body>
</html>
