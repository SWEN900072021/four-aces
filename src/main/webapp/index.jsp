<%@ page import="com.example.domain.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
    if( user == null ) {
        response.sendRedirect("login.jsp");
    }else{
%>
<h1>Username: <%= user.getUsername() %>
</h1>
<%
    }
%>
</body>
</html>