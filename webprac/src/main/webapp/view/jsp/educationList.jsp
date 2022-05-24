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
<title>List of educations</title>
</head>
<body>
<h1>Educations:</h1>

<h2>Add new education</h2>
<form name="addEd" action="edListAdd" method="POST" >
    <ul>
        <li><label>Name:</label> <input type='text' name="edName" /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn" id="addEdButton"></li>
    </ul>
</form>

<h2>List of all educations</h2>

<p>Total educations number:</p>
<p id="edListSize">
${educations.size()}
</p>
<table cellspacing="2" border="1" cellpadding="5" width="720">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th></th>
    </tr>
    <c:forEach var="education" items="${educations}">
    <tr>
        <td>
            <c:out value="${education.getEd_id()}" />
        </td>
        <td>
            <c:out value="${education.getName()}" />
        </td>
        <td>
        	<a href = "viewEd?id=${education.getEd_id()}">view</a> /
            <a href = "deleteEd?id=${education.getEd_id()}">delete</a>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>