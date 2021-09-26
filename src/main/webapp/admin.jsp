<%@ page import="com.example.domain.Admin" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.domain.Airline" %>
<%@ page import="com.example.exception.TRSException" %>
<%@ page import="com.example.domain.User" %>
<%@ page import="com.example.util.HTMLFormatter" %>
<%@ page import="com.example.domain.Customer" %><%--
  Created by IntelliJ IDEA.
  User: yiyuan
  Date: 21/09/2021
  Time: 10:00 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <style>
        table, th, td{
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<%@include file="components/admin-header.jsp" %>
<%
    String error = (String) request.getAttribute("error");
%>
<%
    // Admin admin = (Admin) session.getAttribute("admin");
    if (false) {
        // response.sendRedirect("adminLogin.jsp");
    } else {
        String view = (String) request.getAttribute("view");
        if (view != null) {
            @SuppressWarnings("unchecked")
            ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
            if (users == null) {
                request.setAttribute("error", new TRSException("No " + view + " found in the system"));
            } else {
%>
<%--<p>Username: <%=admin.getUsername()%>--%>
<%--</p>--%>
<%
    if (view.equals("user")) {
%>
<a href="${pageContext.request.contextPath}/fourAces?command=ManageAirline"><div>View All Airline</div></a>
<a href="${pageContext.request.contextPath}/fourAces?command=ManageCustomer"><div>View All Customers</div></a>
<%
    }
%>
<table>
    <tbody>
    <tr>
        <th>id</th>
        <%=view.equals("user") ? HTMLFormatter.tag("th", "type") : ""%>
        <th>email</th>
        <th>username</th>
        <%=view.equals("airline") ? HTMLFormatter.tag("th", "name") : ""%>
        <%=view.equals("airline") ? HTMLFormatter.tag("th", "pending") : ""%>
        <%=view.equals("customer") ? HTMLFormatter.tag("th", "First Name") : ""%>
        <%=view.equals("customer") ? HTMLFormatter.tag("th", "Last Name") : ""%>
    </tr>
    <%
        for (User user : users) {
    %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <%=view.equals("user") ? HTMLFormatter.tag("td", user instanceof Airline ? "Airline" : "Customer") : ""%>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getUsername()%>
        </td>
        <%= view.equals("airline") && user instanceof Airline ? HTMLFormatter.tag("td", ((Airline) user).getName()) : ""%>
        <%
            if (view.equals("airline") && user instanceof Airline) {
        %>
        <td>
            <form action="fourAces?command=ManageAirline" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="checkbox" name="pending"
                       <%if (((Airline) user).isPending())%>checked
                       onclick="this.form.submit()"
                />
            </form>
        </td>
        <%
            }
        %>
        <%= view.equals("customer") && user instanceof Customer ? HTMLFormatter.tag("td", ((Customer) user).getFirstName()) : ""%>
        <%= view.equals("customer") && user instanceof Customer ? HTMLFormatter.tag("td", ((Customer) user).getLastName()) : ""%>
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
<%
    if
    (
            error
                    !=
                    null
    ) {
%>
<p style="color: red"><%=error%>
</p>
<%
    }
%>
</body>
</html>
