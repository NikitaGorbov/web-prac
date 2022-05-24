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
<title>List of companies</title>
</head>
<body>
<h1>Companies:</h1>

<h2>Add new company</h2>
<form name="addComp" action="companyListAdd" method="POST" >
    <ul>
        <li><label>Name:</label> <input type='text' name='compName' /></li>
        <li><label>Description:</label> <input type='text' name='compDescr' /></li>
        <li><label>Location:</label> <input type='text' name='compLocation' /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn" id="addCompButton"></li>
    </ul>
</form>

<h2>List of all companies</h2>

<p>Total companies number:</p>
<p id="compListSize">
${companies.size()}
</p>
<table cellspacing="2" border="1" cellpadding="5" width="720">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Location</th>
        <th></th>
    </tr>
    <c:forEach var="company" items="${companies}">
    <tr>
        <td>
            <c:out value="${company.getComp_name()}" />
        </td>
        <td>
            <c:out value="${company.getDescription()}" />
        </td>
        <td>
            <c:out value="${company.getLocation()}" />
        </td>
        <td>
        	<a href = "viewComp?id=${company.getComp_id()}">view</a> /
            <a href = "deleteComp?id=${company.getComp_id()}">delete</a>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>