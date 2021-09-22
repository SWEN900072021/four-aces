<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h2>Edit Flight</h2>
<form action = "fourAces?command=EditFlight&id=<%=request.getParameter("id")%>" method = "post">
    Flight code: <input type = "text" name = "flightCode" value = <%=request.getParameter("code")%>><br/>
    Flight date: <input type = "text" name = "flightDate" value = <%=request.getParameter("date")%>><br/>
    Flight time: <input type = "text" name = "flightTime" value = <%=request.getParameter("time")%>><br/>
    <input type = "submit" value = "Save Edit">
</form>
</body>
</html>
