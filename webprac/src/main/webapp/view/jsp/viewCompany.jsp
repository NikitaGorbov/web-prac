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
<title>Company page</title>
</head>
<body>
<h2>View company's vacancies:</h2>
<a href = "companyVacs?id=${company.getComp_id()}">List of vacancies</a>
<h2>Edit company:</h2>
<p>

<form name="editComp" action="companyEdit" method="POST" >
	<input type="hidden" name='compId' value="${company.getComp_id()}"/>
    <ul>
        <li><label>Name:</label> <input type='text' name='compName' value="${company.getComp_name()}" /></li>
        <li><label>Description:</label> <input type='text' name='compDescr' value="${company.getDescription()}" /></li>
        <li><label>Location:</label> <input type='text' name='compLocation' value="${company.getLocation()}" /></li>
        <li><label>&nbsp;</label> <input type="submit" value="Save" class="btn" id="saveButton"></li>
    </ul>
</form>

</body>
</html>