<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/fourAces?command=ManageUser">View All Users</a>
<button onclick="window.location.href = 'createAirport.jsp'">Create Airport</button>
<button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=GetAirport'">View Airport</button>
</body>
</html>
