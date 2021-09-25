<%@ page import="com.example.domain.Passenger" %><%--
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
    <h2>Enter Passenger Detail</h2>

    <%
        int customerId = Integer.parseInt(request.getParameter("customerId"));
    %>

    <form action="fourAces?command=AddPassenger&customerId=<%= customerId%>" method = "post">
        <label for="firstName">First Name: </label><input type="text" id="firstName" name="firstName"><br>
        <label for="lastName">Last Name: </label><input type="text" id="lastName" name="lastName"><br>
        <label for="idType">Identification Type: </label><input type="text" id="idType" name="idType"><br>
        <label for="idNum">Identification Number: </label><input type="text" id="idNum" name="idNum"><br>
        <input type="submit" value="Add Passenger">
    </form>

    <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=GetAvailableSeats&type=go&customerId=<%=customerId%>'">Proceed to choosing seats</button>

</body>
</html>
