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

<form action="fourAces?command=AddPassenger" method="post">
    <label for="firstName">First Name: </label><input type="text" id="firstName" name="firstName"><br>
    <label for="lastName">Last Name: </label><input type="text" id="lastName" name="lastName"><br>
    <label for="idType">Identification Type: </label><input type="text" id="idType" name="idType"><br>
    <label for="idNum">Identification Number: </label><input type="text" id="idNum" name="idNum"><br>
    <input type="submit" value="Add Passenger">
</form>

<a href="<%= request.getContextPath()%>/fourAces?command=SubmitBooking">
    <button>Submit booking</button>
</a>
<a href="<%= request.getContextPath()%>/fourAces?command=CancelBooking">
    <button>Cancel</button>
</a>

</body>
</html>
