<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of positions</title>
</head>
<body>
<h1>Positions:</h1>

<h2>Add new Position</h2>
<form name="addPos" action="posListAdd" method="POST" >
    <ul>
        <li><label>Name:</label> <input type='text' name='posName' /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn" id="addPosButton"></li>
    </ul>
</form>

<h2>List of all positions</h2>

<p>Total positions number:</p>
<p id="posListSize">
${positions.size()}
</p>
<table cellspacing="2" border="1" cellpadding="5" width="720">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th></th>
    </tr>
    <c:forEach var="position" items="${positions}">
    <tr>
        <td>
            <c:out value="${position.getPos_id()}" />
        </td>
        <td>
            <c:out value="${position.getPosition_name()}" />
        </td>
        <td>
        	<a href = "viewPos?id=${position.getPos_id()}">view</a> /
            <a href = "deletePos?id=${position.getPos_id()}">delete</a>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>