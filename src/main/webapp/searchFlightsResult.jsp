<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.Flight" %>
<%@ page import="com.example.domain.Airport" %>
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
    List<Flight> flights = (List<Flight>) request.getAttribute("flights");
    if (flights.size() == 0) {
%>
    <h2>No flight found</h2>
<%
    } else {
%>

<div align="left">
    <table border="1" cellpadding="5">
        <caption><h2>List of flights</h2></caption>
        <thead>
        <tr>
            <th>Flight ID</th>
            <th>Flight Code</th>
            <th>Flight Date</th>
            <th>Flight Time</th>
            <th>Source Airport</th>
            <th>Destination Airport</th>
            <th>View</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Flight flight : flights) {
        %>
        <tr>
            <td><%= flight.getId()%>
            </td>
            <td><%= flight.getCode()%>
            </td>
            <td><%= flight.getDate()%>
            </td>
            <td><%= flight.getTime()%>
            </td>
            <td><%= flight.getSource().getReferenceCode()%>
            </td>
            <td><%= flight.getDestination().getReferenceCode()%>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/fourAces?command=ViewFlight" method="post">
                    <input type="hidden" name="flightId" value=<%=flight.getId()%>>
                    <input type="hidden" name="type" value="go">
                    <button type="submit">View</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/fourAces?command=Customer"><button>Back to Homepage</button></a>
</div>
<%
    }
%>
</body>
</html>
