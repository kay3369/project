<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Project_Board</title>
	<link rel="stylesheet" href="${path}/board/css/style.css">
	<script  src="${path}/script/jquery-3.4.1.min.js"></script>
	<script>
		$(function(){
			console.log('jquery ok...!');
			
			$('#btnUpdate').click(function(){
				console.log('btnUpdate ok...!');
				
				document.form1.action = "${path}/board_servlet/update.do";
				document.form1.submit();
			});
			
			$('#btnDelete').click(function(){
				console.log('btnDelete ok...!');
				
				if (confirm('삭제하시겠습니까?')){
					document.form1.action = "${path}/board_servlet/delete.do";
					document.form1.submit();
				}
			});
			$('#btnList').click(function(){
				console.log('btnList ok...!');
			});
		});
	</script>
</head>
<body>
	<h2>게시물 수정 삭제</h2>
	<form name = "form1" method = "post">
		<table style = "margin : 0 auto">
			<tr>
				<td>이름</td>
				<td><input type="text" name="writer" id="writer" value="${dto.writer }" /></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" id="subject" value="${dto.subject }" /></td>
			</tr>
			<tr>
				<td>본문</td>
				<td><textarea name="content" id="content" cols="60" row="5">${dto.content}</textarea></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="passwd" id="passwd" />
					<c:if test="${param.pass_error == 'y '}">
						<span style="color:red">비밀번호가 일치하지 않습니다.</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="button" id="btnUpdate" value="수정" />
					<input type="button" id="btnDelete" value="삭제" />
					<input type="button" id="btnList" value="목록" />
					<input type="hidden" name="num" value="${dto.num }" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>