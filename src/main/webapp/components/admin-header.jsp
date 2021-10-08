<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/fourAces?command=ManageUser"><button>View All Users</button></a>
<a href="<%= request.getContextPath()%>/fourAces?command=CreateAirport"><button>Create Airport</button></a>
<a href="<%= request.getContextPath()%>/fourAces?command=GetAirport"><button>View Airport</button></a>
<%@include file="navbar.jsp"%>
</body>
</html>
