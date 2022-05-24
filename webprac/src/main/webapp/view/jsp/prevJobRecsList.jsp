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
<title>Previous job records</title>
</head>
<body>

<h2>Add new record</h2>
<form name="addPjr" action="pjrListAdd" method="POST" >
    <ul>
        <li><label>Applicant:</label> <input type='text' name="appl" value="${applId}"/></li>
        <li><label>Company:</label> <input type='text' name="comp" /></li>
        <li><label>Position:</label> <input type='text' name="pos" /></li>
        <li><label>Duration:</label> <input type='text' name="dur" /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn" id="addPjrButton"></li>
    </ul>
</form>

<h2>List of records</h2>

<p>Total  number:</p>
<p id="pjrListSize">
${pjrs.size()}
</p>
<table cellspacing="2" border="1" cellpadding="5" width="720">
    <tr>
        <th>Applicant</th>
        <th>Company</th>
        <th>Position</th>
        <th>Duration</th>
        <th></th>
    </tr>
    <c:forEach var="pjr" items="${pjrs}">
    <input type="hidden" name='pjrId' value="${pjr.getPrev_job_record_id()}"/>
    <tr>
        <td>
            <c:out value="${pjr.getApplicant().getAppl_name()}" />
        </td>
        <td>
            <c:out value="${pjr.getCompany().getComp_name()}" />
        </td>
        <td>
            <c:out value="${pjr.getPosition().getPosition_name()}" />
        </td>
        <td>
            <c:out value="${pjr.getDuration()}" />
        </td>
        <td>
        	<a href = "viewPjr?id=${pjr.getPrev_job_record_id()}">view</a> /
            <a href = "deletePjr?id=${pjr.getPrev_job_record_id()}">delete</a>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>