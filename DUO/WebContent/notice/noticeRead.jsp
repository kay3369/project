<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" /> 
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../script/jquery-3.4.1.min.js"></script>
<script>
	$(function(){
			console.log('jQuery ok.....');
			
			$('#btnSave').click(function(){
				console.log('click ok.....');
				location.href="${path }/notice_servlet/list.do"; 
			});
			
			$('#btnEdit').click(function(){
				console.log('Edit..ok');

				document.form1.action="${path }/notice_servlet/edit.do";
				document.form1.submit();
				
			});
			
	
		});
</script>
</head>
<body>
<%@include file="../include/header.jsp" %>
<%@include file="../include/menu.jsp" %>

<form action="" name="form1" method="post" >
		<table>
			<tr>
				<td>작성자</td>
				<td>${dto.n_no }</td> <!-- 되도록이면 DB랑 이름똑같이  DTO도  -->
			</tr>
			<tr>
				<td>제목</td>
				<td>${dto.n_title }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="n_detail" id="content" cols="60" row="5" readonly="readonly">${dto.n_detail }</textarea> </td>
			</tr>
			
			<tr>
				<td colspan="2">
				<input type="button" name="btnSave" id="btnSave" value="목록" />
				<!-- 나중에 조건문을 걸어주세요! -->
					<input type="hidden" name="n_no" value="${dto.n_no }" />
				<input type="button" value="수정/삭제" id="btnEdit"/>  </td>
					 	 	 	 	 	 	 
			</tr>	 
		</table>
	</form>

<%@include file="../include/footer.jsp" %>
</body>
</html>