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
<title>Education page</title>
</head>
<body>
<h2>Edit education:</h2>
<p>

<form name="editEducation" action="educationEdit" method="POST" >
	<input type="hidden" name='edId' value="${education.getEd_id()}"/>
    <ul>
        <li><label>Name:</label> <input type='text' name='edName' value="${education.getName()}" /></li>
        <li><label>&nbsp;</label> <input type="submit" value="Save" class="btn" id="saveButton"></li>
    </ul>
</form>

</body>
</html>