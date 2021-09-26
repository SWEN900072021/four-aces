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
<h2>Booking successful</h2>
<%
    if (request.getParameter("customerId") != null) {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
%>
<button onclick="window.location.href = '<%= request.getContextPath()%>/customer.jsp?customerId=<%=customerId%>'">Back to Homepage</button>
<%
    }
%>
</body>
</html>
