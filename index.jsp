<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path" value="${pageContext.request.contextPath }"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
	<h2>회원 전용 페이지</h2> <hr />
	<form action="/logout.do">
		<table>
			<tr>
			<c:if test="${loginUser.name !=null}">
				<td>안녕하세요. ${loginUser.name }(${loginUser.userid })님 환영합니다!</td>
			 
			</c:if>
			<a href="${path }/board_servlet/list.do">게시판</a>
			</tr>
			<tr>
				<td>
				<c:if test="${loginUser.name !=null}">
					<input type="submit" value="로그아웃" />
					<input type="button" value="회원정보수정" onclick="location.href='memberUpdate.do?userid=${loginUser.userid}'" />
				</c:if>
			
				</td>
			</tr>
		</table>
		
	</form>		
	<input type="submit" value="로그인" onclick="location.href='${path}/login.do'"/>	

</body>
</html>