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
<title>List of applicants</title>
</head>
<body>
<h1>Applicants:</h1>

<h2>Add new applicant</h2>
<form name="addAppl" action="applicantListAdd" method="POST" >
    <ul>
        <li><label>Name:</label> <input type='text' name='applName' /></li>
        <li><label>Education:</label> <input type='text' name='applEd' /></li>
        <li><label>Status:</label> <input type='text' name='applStatus' /></li>
        <li><label>Address:</label> <input type='text' name='applAddress' /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn"></li>
    </ul>
</form>

<h2>List of all applicants</h2>
<table cellspacing="2" border="1" cellpadding="5" width="720">
    <tr>
        <th>Name</th>
        <th>Education</th>
        <th>Status</th>
        <th>Address</th>
        <th></th>
    </tr>
    <c:forEach var="applicant" items="${applicants}">
    <tr>
        <td>
            <c:out value="${applicant.getAppl_name()}" />
        </td>
        <td>
            <c:out value="${applicant.getEducation().getName()}" />
        </td>
        <td>
            <c:out value="${applicant.getStatus()}" />
        </td>
        <td>
            <c:out value="${applicant.getAddress()}" />
        </td>
        <td>
        	<a href = "viewAppl?id=${applicant.getAppl_id()}">view</a> /
            <a href = "deleteAppl?id=${applicant.getAppl_id()}">delete</a>
            
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>