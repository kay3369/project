<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="path" value="${pageContext.request.contextPath }"></c:set>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자  </title>
</head>
<body>
	<table>
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>제목</td>
				<td>날짜</td>
				<td>조회수</td>
				<td>삭제</td>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr>
		            <td>${dto.num}</td>
					<td>${dto.writer}</td>
					<!-- 나중에 페이징 번호 넣을 곳 -->
					<td>
						<a href="${path}/board_servlet/view.do?num=${dto.num}">${dto.subject}[${dto.comment_count}]</a>
					</td>
					
					<td>${dto.reg_date}</td>
					<td>${dto.readcount}</td>
					<td><input type="button" id="btnDel" name="btnDel" value="삭제"></td>
				</tr>
			</c:forEach>
			
			
	</table>
</body>
</html>