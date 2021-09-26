<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS | Login</title>
</head>
<body>
<%
    if (Objects.equals(request.getParameter("type"),"admin")) response.sendRedirect("adminLogin.jsp");
%>
<%
    String error = (String) request.getAttribute("error");
%>
<h1>Airline & Customer Login</h1>
<form action="fourAces?command=Login" method="post">
    <table>
        <tr>
            <td>Email</td>
            <td><input type="text" id="email" name="email"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" id="password" name="password"></td>
        </tr>
        <tr>
            <td>
                <label for="type">Login As</label>
            </td>
            <td>
                <select name="type" id="type">
                    <option value="customer">Customer</option>
                    <option value="airline">Airline</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Login"></td>
        </tr>
    </table>
</form>
<p>Don't have an account? <a href="register.jsp"><button>Register</button></a></p>
<%
    if (error != null) {
%>
<p style="color: red"><%=error%>
</p>
<%
    }
%>
</body>
</html>

