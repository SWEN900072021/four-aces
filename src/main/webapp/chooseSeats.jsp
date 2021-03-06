<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page import="com.example.domain.Ticket" %>
<%@ page import="com.example.domain.Passenger" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<%@include file="components/navbar.jsp"%>
<%
    if( session.getAttribute("auth") == null )
        response.sendRedirect("fourAces?command=Customer");
%>
<%
    @SuppressWarnings("unchecked")
    List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
    String type = (String) request.getAttribute("type");
    boolean returning = (boolean) request.getAttribute("returning");
    if (!returning) {
%>
<h2>Please select seat</h2>
<%
    } else if (type.equals("go")) {
%>
<h2>Please select the seat for your outgoing flight</h2>

<%
    } else {
%>
<h2>Please select the seat for your returning flight</h2>
<%
    }
%>


<div align="left">
    <%
        if (tickets.size() == 0) {
    %>
    <h5> There are no more seats available for this flight. Please try booking another flight </h5>
    <%
    } else {
    %>
    <form action="fourAces?command=SelectSeats" method="post">
        <input type="hidden" name="type" value=<%=type%>>
    <table style="border: 1px solid black; border-collapse: collapse">
        <tbody>
        <tr>
            <th>Class</th>
            <th>Seat number</th>
            <th>Select</th>
        </tr>
        <%
            for (Ticket ticket : tickets) {
        %>
        <tr>
            <td><%=ticket.getSeatClass()%>
            </td>
            <td><%=ticket.getSeatNumber()%>
            </td>
            <td>
                <input type="radio" name="select" value="<%=ticket.getId()%>"/>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
        <input type="submit" value="Select Seat">
    </form>
    <%
        }
    %>
    <a href="<%= request.getContextPath()%>/fourAces?command=CancelBooking">
        <button>Cancel</button>
    </a>
</div>

<%
    String error = (String) session.getAttribute("error");
    if( error != null ){
%>
<p style="color: red"><%=error%></p>
<%
    }
%>
</body>
</html>

