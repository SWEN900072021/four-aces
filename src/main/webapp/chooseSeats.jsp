<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.domain.Airport" %>
<%@ page import="com.example.domain.Ticket" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS</title>
</head>
<body>
<h2>Please select seats</h2>
<%
    List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
    int customerId = Integer.parseInt(request.getParameter("customerId"));
    String type = (String) request.getAttribute("type");
%>

<div align="left">
    <form action="fourAces?command=SelectSeats?customerId=<%=customerId%>&type=<%=type%>>" method="post">
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
                <input type="checkbox" name="select" value="<%=ticket.getId()%>"/>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    </form>
</div>
</body>
</html>

