<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%@include file="components/admin-header.jsp"%>
<h2>Create Airports</h2>
<form action = "fourAces?command=CreateAirport" method = "post">
    Reference Code: <input type="text" name="referenceCode"> <br>
    Address: <input type="text" name="address"> <br>
    <input type = "submit" value = "Create Airport">
</form>
</body>
</html>
