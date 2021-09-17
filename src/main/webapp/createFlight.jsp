<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h2>Create Flight</h2>
<form action = "frontServlet?command=CreateFlight" method = "post">
    Flight code: <input type = "text" name = "flightCode"><br/>
    Flight date: <input type = "text" name = "flightDate"><br/>
    Flight time: <input type = "text" name = "flightTime"><br/>
    <input type = "submit" value = "Create Flight">
</form>
</body>
</html>
