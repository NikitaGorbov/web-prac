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
<title>Position page</title>
</head>
<body>
<h2>Edit position:</h2>
<p>

<form name="editPosition" action="positionEdit" method="POST" >
	<input type="hidden" name='posId' value="${position.getPos_id()}"/>
    <ul>
        <li><label>Name:</label> <input type='text' name='posName' value="${position.getPosition_name()}" /></li>
        <li><label>&nbsp;</label> <input type="submit" value="Save" class="btn" id="saveButton"></li>
    </ul>
</form>

</body>
</html>