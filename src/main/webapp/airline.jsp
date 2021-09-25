<%@ page import="com.example.domain.Airline" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.exception.TRSException" %>
<%@ page import="com.example.domain.Passenger" %>
<%@ page import="com.example.domain.User" %>
<%@ page import="jdk.internal.access.JavaLangInvokeAccess" %>
<%@ page import="jdk.internal.loader.Resource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
<%@include file="components/admin-header.jsp" %>
<%
  String error = (String) request.getAttribute("error");
%>
<%
  int airlineId = Integer.parseInt(request.getParameter("airlineId"));

</p>
<table style="border: 1px solid black; border-collapse: collapse">
  <tbody>
  <tr>
    <th>firstName</th>
    <th>lastName</th>
    <th>idType</th>
    <th>idNumber</th>
  </tr>
  <%
    for (Passenger passenger : passengers) {
  %>
  <tr>
    <%--        <td><%=user instanceof Airline ? "Airline" : "Customer"%></td>--%>
    <td><%=passenger.getfirstName()%>
    </td>
    <td><%=passenger.getlastName()%>
    </td>
    <td><%=passenger.getIdentificationType()%>
    </td>
    <td><%=passenger.getIdentificationNumber()%>
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
<%
  if( error != null ){
%>
<p style="color: red"><%=error%></p>
<%
  }
%>

  <h2>Airline Home Page</h2>
  <h3>You have successfully logged in</h3>
  <button onclick="window.location.href = 'createFlight.jsp?airlineId=<%=airlineId%>'">Create Flight</button>
  <button onclick="window.location.href = '<%= request.getContextPath()%>/fourAces?command=GetFlight&airlineId=<%=airlineId%>'">View Flight</button>
</body>
</html>