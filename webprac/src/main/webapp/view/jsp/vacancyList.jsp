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
<title>List of vacancies</title>
</head>
<body>
<h1>Vacancies:</h1>

<h2>Add new Vacancy</h2>
<form name="addVac" action="vacListAdd" method="POST" >
    <ul>
        <li><label>Company:</label> <input type='text' name='compId' /></li>
        <li><label>Position:</label> <input type='text' name='posId' /></li>
        <li><label>Requirements:</label> <input type='text' name='req' /></li>
        <li><label>Salary:</label> <input type='text' name='sal' /></li>
        <li><label>Exp required:</label> <input type='text' name='exp' /></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" class="btn" id="addVacancyButton"></li>
    </ul>
</form>

<h2>List of all vacancies</h2>

Filter:
<form name="filterVacancy" action="filterVacancy" method="POST" >
		Position: <input type='text' name='posFilter' />
        <input type="submit" value="OK" class="btn" id="filter">
</form>

<p>Total vacancies number:</p>
<p id="vacListSize">
${vacancies.size()}
</p>
<table cellspacing="2" border="1" cellpadding="5" width="720">
    <tr>
        <th>Company</th>
        <th>Position</th>
        <th>Requirements</th>
        <th>Salary</th>
        <th>Exp required</th>
        <th></th>
    </tr>
    <c:forEach var="vacancy" items="${vacancies}">
    <tr>
        <td>
            <c:out value="${vacancy.getCompany().getComp_name()}" />
        </td>
        <td>
            <c:out value="${vacancy.getPosition().getPosition_name()}" />
        </td>
        <td>
            <c:out value="${vacancy.getRequirements()}" />
        </td>
        <td>
            <c:out value="${vacancy.getSalary()}" />
        </td>
        <td>
        	<c:out value="${vacancy.getExp_required()}" />
        </td>
        <td>
        	<a href = "viewVacancy?id=${vacancy.getVac_id()}">view</a> /
            <a href = "deleteVacancy?id=${vacancy.getVac_id()}">delete</a>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>