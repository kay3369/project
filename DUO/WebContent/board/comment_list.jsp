<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="path" value="${pageContext.request.contextPath}"/>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- 숫자, 날짜 형식 -->
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project_Board</title>
</head>
<body>
<table>
	<c:forEach var="row" items="${list }">
		<tr>
			<td>
				${row.writer} [<fmt:formatDate value="${row.reg_date }" pattern="yyyy-mm-dd hh:mm:ss"/>]<br>
				${row.content}
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>