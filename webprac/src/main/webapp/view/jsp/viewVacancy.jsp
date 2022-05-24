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
<title>Vacancy page</title>
</head>
<body>
<h2>Edit Vacancy:</h2>
<p>

<form name="editVac" action="vacEdit" method="POST" >
	<input type="hidden" name='vacId' value="${vacancy.getVac_id()}"/>
    <ul>
        <li><label>Company:</label> <input type='text' name='compId' value="${vacancy.getCompany().getComp_id()}"/></li>
        <li><label>Position:</label> <input type='text' name='posId' value="${vacancy.getPosition().getPos_id()}" /></li>
        <li><label>Requirements:</label> <input type='text' name='req' value="${vacancy.getRequirements()}" /></li>
        <li><label>Salary:</label> <input type='text' name='sal' value="${vacancy.getSalary()}"/></li>
        <li><label>Exp required:</label> <input type='text' name='exp' value="${vacancy.getExp_required()}"/></li>
        <li><label>&nbsp;</label> <input type="submit" value="Save" class="btn" id="saveButton"></li>
    </ul>
</form>

<p>
<a href = "relevantCvs?id=${vacancy.getVac_id()}">Get relevant CVs</a>
</p>


</body>
</html>