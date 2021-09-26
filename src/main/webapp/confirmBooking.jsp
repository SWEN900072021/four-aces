<%--
  Created by IntelliJ IDEA.
  User: tienhinh
  Date: 25/9/21
  Time: 4:49 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if( session.getAttribute("auth") == null )
        response.sendRedirect("fourAces?command=Customer");
%>
<h2>Booking successful</h2>
<button onclick="window.location.href = '<%= request.getContextPath()%>/customer.jsp'">Back to Homepage</button>
</body>
</html>
