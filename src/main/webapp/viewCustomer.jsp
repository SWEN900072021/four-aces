<%@ page import="com.example.domain.Customer" %>
<%@ page import="java.util.ArrayList" %><%--
<%--
  Created by IntelliJ IDEA.
  User: yuxiangwu
  Date: 7/10/21
  Time: 1:11 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Customer</title>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<%
    if (session.getAttribute("auth") == null)
        response.sendRedirect("fourAces?command=Airline");
%>
<%
    ArrayList<Customer> customers;
    if ( (customers = (ArrayList<Customer>) request.getAttribute("customers")) == null)
        response.sendRedirect("fourAces?command=Airline");
    else {
%>
<table border="1" cellpadding="5">
    <tr>
        <th>customerFirstName</th>
        <th>customerLastName</th>
    </tr>
    <%
        for (Customer customer : customers){
    %>
    <tr>
        <td><%=customer.getFirstName()%></td>
        <td><%=customer.getLastName()%></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>

