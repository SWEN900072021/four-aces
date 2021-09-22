<%@ page import="com.example.domain.Admin" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.domain.Airline" %>
<%@ page import="com.example.exception.TRSException" %><%--
  Created by IntelliJ IDEA.
  User: yiyua
  Date: 21/09/2021
  Time: 10:00 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<%@include file="components/admin-header.jsp" %>
<%
    Admin admin = Admin.getAdmin();
    if (admin.getUsername() == null) {
        response.sendRedirect("adminLogin.jsp");
    } else {
        if (request.getAttribute("command") != null &&
        request.getAttribute("command").equals("view airline")) {
            @SuppressWarnings("unchecked")
            ArrayList<Airline> airlines = (ArrayList<Airline>) request.getAttribute("airlines");
            if (airlines == null) {
                request.setAttribute("error", new TRSException("No airline"));
            } else {
%>
<p>Username: <%=admin.getUsername()%>
</p>
<table style="border: 1px solid black; border-collapse: collapse">
    <tbody>
    <tr>
        <th>id</th>
        <th>email</th>
        <th>username</th>
        <th>pending</th>
    </tr>
    <%
        for (Airline airline : airlines) {
    %>
    <tr>
        <td><%=airline.getId()%>
        </td>
        <td><%=airline.getEmail()%>
        </td>
        <td><%=airline.getUsername()%>
        </td>
        <td>
            <form action="fourAces?command=ManageAirline" method="post">
                <input type="hidden" name="id" value="<%=airline.getId()%>">
                <input type="checkbox" name="pending"
                       <%if (airline.isPending())%>checked
                       onclick="this.form.submit()"
                />
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
            }
        }
    }
%>
</body>
</html>
