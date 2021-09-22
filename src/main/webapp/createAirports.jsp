<%--
  Created by IntelliJ IDEA.
  User: yuxiangwu
  Date: 22/9/21
  Time: 1:22 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h2>Create Airports</h2>
<form action = "fourAces?command=CreateAirport" method = "post">
    Reference Code: <input type="text" name="referenceCode"> <br>
    Address: <input type="text" name="address"> <br>
    <input type = "submit" value = "Create Airport">
</form>
</body>
</html>
