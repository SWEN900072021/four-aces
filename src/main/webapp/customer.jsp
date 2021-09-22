<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>TRS</title>
</head>
<body>
  <h2>Customer Home Page</h2>
  <h3>You have successfully logged in</h3>

  <form action="fourAces?command=SearchFlights" method="post">
    <label for="date">Date: </label><input type="text" id="date" name="date"><br>
    <label for="date">Time: </label><input type="text" id="time" name="time"><br>
    <input type="submit" value="Search Flights">
  </form>

</body>
</html>