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
<title>List of CVs</title>
</head>
<body>
<h1>CVs:</h1>

<h2>Add new CV</h2>
<form name="addCv" action="cvListAdd" method="POST" >
    <ul>
        <li><label>Applicant:</label> <input type='text' name='appl' /></li>
        <li><label>Objective:</label> <input type='text' name='obj' /></li>
        <li><label>Experience:</label> <input type='text' name='exp' /></li>
        <li><label>Desired salary:</label> <input type='text' name='sal' /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn" id="addCvButton"></li>
    </ul>
</form>

<h2>List of all CVs</h2>

Filter:
<form name="filterCv" action="filterCv" method="POST" >
		Position: <input type='text' name='posFilter' />
        <input type="submit" value="OK" class="btn" id="filter">
</form>

<p>Total CVs number:</p>
<p id="cvListSize">
${cvs.size()}
</p>
<table cellspacing="2" border="1" cellpadding="5" width="720">
    <tr>
        <th>Applicant</th>
        <th>Objective</th>
        <th>Experience</th>
        <th>Desired salary</th>
        <th></th>
    </tr>
    <c:forEach var="cv" items="${cvs}">
    <tr>
        <td>
            <c:out value="${cv.getApplicant().getAppl_name()}" />
        </td>
        <td>
            <c:out value="${cv.getObjective().getPosition_name()}" />
        </td>
        <td>
            <c:out value="${cv.getWork_exp()}" />
        </td>
        <td>
            <c:out value="${cv.getDesired_salary()}" />
        </td>
        <td>
        	<a href = "viewCv?id=${cv.getCv_id()}">view</a> /
            <a href = "deleteCv?id=${cv.getCv_id()}">delete</a>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>