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
<title>CV page</title>
</head>
<body>
<h2>Edit CV:</h2>
<p>

<form name="editCv" action="cvEdit" method="POST" >
	<input type="hidden" name='cvId' value="${cv.getCv_id()}"/>
    <ul>
        <li><label>Applicant:</label> <input type='text' name='appl' value="${cv.getApplicant().getAppl_id()}" /></li>
        <li><label>Objective:</label> <input type='text' name='obj' value="${cv.getObjective().getPos_id()}" /></li>
        <li><label>Experience:</label> <input type='text' name='exp' value="${cv.getWork_exp()}" /></li>
        <li><label>Desired salary:</label> <input type='text' name='sal' value="${cv.getDesired_salary()}" /></li>
        <li><label>&nbsp;</label> <input type="submit" value="Save" class="btn" id="saveButton"></li>
    </ul>
</form>

<p>
<a href = "relevantVacs?id=${cv.getCv_id()}">Get relevant vacancies</a>
</p>

</body>
</html>