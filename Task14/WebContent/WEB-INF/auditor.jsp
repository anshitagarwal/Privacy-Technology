<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Auditor page</title>
</head>
<body>
	<form action="auditor">
		<input type="submit" name="act" value="origin"/>
		<input type="submit" name="act" value="consent"/>
		<input type="submit" name="act" value="noconsent"/>
	</form>
	<table>
		<tbody>
			<c:forEach var = "record" items = "${records }">
			<tr>
			<td>${record.diseaseName }</td>
			<td>${record.diseaseTreats }</td>
			<td>${record.chemicalName }</td>
			</tr>
	</c:forEach>
		</tbody>
	</table>
</body>
</html>