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
<title>Applicant page</title>
</head>
<body>
<h2>View applicant's CVs:</h2>
<a href = "LINK_TO_CVS">List of CVs</a>
<h2>Edit applicant:</h2>
<p>

<form name="editAppl" action="applicantEdit" method="POST" >
	<input type="hidden" name='applId' value="${applicant.getAppl_id()}"/>
    <ul>
        <li><label>Name:</label> <input type='text' name='applName' value="${applicant.getAppl_name()}" /></li>
        <li><label>Education:</label> <input type='text' name='applEd' value="${applicant.getEducation().getEd_id()}" /></li>
        <li><label>Status:</label> <input type='text' name='applStatus' value="${applicant.getStatus()}" /></li>
        <li><label>Address:</label> <input type='text' name='applAddress' value="${applicant.getAddress()}" /></li>
        <li><label>&nbsp;</label> <input type="submit" value="Save" class="btn"></li>
    </ul>
</form>

</p>
</body>
</html>