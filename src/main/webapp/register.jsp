<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS | Register</title>
</head>
<body>
    <%
        String error = (String) request.getAttribute("error");
    %>
    <h1>Airline & Customer Registration</h1>
    <form action="fourAces?command=Register" method="post">
        <table>
            <tr>
                <td>Username</td>
                <td><input type="text" id="username" name="username"></td>
            </tr>
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
                    <label for="type">Register As</label>
                </td>
                <td>
                    <select name="type" id="type">
                        <option value="Customer">Customer</option>
                        <option value="Airline">Airline</option>
                        <option value="Admin">Admin</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Create Account"></td>
            </tr>
        </table>
        <p>Already have an account? <a href="login.jsp">Login</a></p>
    </form>
    <%
        if( error != null ){
    %>
    <p style="color: red"><%=error%></p>
    <%
        }
    %>
</body>
</html>
