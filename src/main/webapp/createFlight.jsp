<%@ page import="com.example.domain.Airport" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.datasource.AirplaneDataMapper" %>
<%@ page import="com.example.domain.Airplane" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRS | Create Flight</title>
</head>
<body>
<%
    if( session.getAttribute("auth") == null )
        response.sendRedirect("fourAces?command=Airline");
%>
    <%
        List<Airport> airports = (List<Airport>) request.getAttribute("airports");
        if (airports.size() > 0) {
    %>
            <h2>Create Flight</h2>
            <form action = "fourAces?command=CreateFlight" method = "post">
                <table>
                    <tr>
                        <td>Flight Code</td>
                        <td><input type = "text" name = "flightCode" placeholder="JQ569"></td>
                    </tr>
                    <tr>
                        <td>Flight Date</td>
                        <td><input type = "date" name = "flightDate" placeholder="1999-12-31"></td>
                    </tr>
                    <tr>
                        <td>Flight Time</td>
                        <td><input type = "time" name = "flightTime" placeholder="21:45"></td>
                    </tr>
                    <tr>
                        <td>
                            <label for="source">Source Airport</label>
                        </td>
                        <td>
                            <select name="source" id="source">
                                <option selected value=""></option>
                                <%
                                    for (Airport airport : airports) {
                                        int airportId = airport.getId();
                                        String referenceCode = airport.getReferenceCode();
                                %>
                                <option value=<%=airportId%>><%=referenceCode%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="destination">Destination Airport</label>
                        </td>
                        <td>
                            <select name="destination" id="destination">
                                <option selected value=""></option>
                                <%
                                    for (Airport airport : airports) {
                                        int airportId = airport.getId();
                                        String referenceCode = airport.getReferenceCode();
                                %>
                                <option value=<%=airportId%>><%=referenceCode%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="stopover1">Stopover 1</label>
                        </td>
                        <td>
                            <select name="stopover1" id="stopover1">
                                <option selected value=""></option>
                                <%
                                    for (Airport airport : airports) {
                                        int airportId = airport.getId();
                                        String referenceCode = airport.getReferenceCode();
                                %>
                                <option value=<%=airportId%>><%=referenceCode%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="stopover2">Stopover 2</label>
                        </td>
                        <td>
                            <select name="stopover2" id="stopover2">
                                <option selected value=""></option>
                                <%
                                    for (Airport airport : airports) {
                                        int airportId = airport.getId();
                                        String referenceCode = airport.getReferenceCode();
                                %>
                                <option value=<%=airportId%>><%=referenceCode%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="stopover3">Stopover 3</label>
                        </td>
                        <td>
                            <select name="stopover3" id="stopover3">
                                <option selected value=""></option>
                                <%
                                    for (Airport airport : airports) {
                                        int airportId = airport.getId();
                                        String referenceCode = airport.getReferenceCode();
                                %>
                                <option value=<%=airportId%>><%=referenceCode%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="airplane">Airplane</label>
                        </td>
                        <td>
                            <select name="airplane" id="airplane">
                                <option selected value=""></option>
                                <%
                                    List<Airplane> airplanes = AirplaneDataMapper.getInstance().getAll();
                                    for (Airplane airplane : airplanes) {
                                        int airplaneId = airplane.getId();
                                        String type = airplane.getType();
                                %>
                                <option value=<%=airplaneId%>><%=type%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type = "submit" value = "Create Flight">
            </form>
    <%
        } else {
    %>
            <p>Sorry, no airports available.</p>
    <%
        }
    %>

    <%
        String error = (String) request.getAttribute("error");
        if (request.getAttribute("error") != null){
    %>
            <p style="color: red"><%=error%></p>
    <%
        }
    %>
</body>
</html>
