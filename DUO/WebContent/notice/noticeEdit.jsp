<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path" value="${pageContext.request.contextPath }"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../script/jquery-3.4.1.min.js"></script>
		<script type="text/javascript">
		$(function(){
			console.log('jquery..ok');
			$('#btnUpdate').click(function(){
				console.log('#btnUpdate..ok');
				
				document.form1.action="${path}/notice_servlet/update.do";
				document.form1.submit();
			});
			$('#btnList').click(function(){
				
				console.log('#btnList..ok');
				document.form1.action="${path}/notice_servlet/list.do";
				document.form1.submit();
			});
			$('#btnDelete').click(function(){
			console.log('##btnDelete..ok');
			
			if(confirm('삭제하시겠습니까')){
				document.form1.action= "${path}/notice_servlet/delete.do";
				document.form1.submit();
			}
			
			});
		});
		</script>
</head>
<body>
<form action="" name="form1" method="post" >
		<table>
			<tr>
				<td>작성자</td>
			<td><input type="text" name="writer" id="writer" value="${dto.n_writer }" readonly="readonly" />  <!-- 되도록이면 DB랑 이름똑같이  DTO도  -->
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="n_title" id="n_title" value="${dto.n_title }" /> </td>
				
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="n_detail" id="n_detail" cols="60" row="5" >${dto.n_detail }</textarea> </td>
			</tr>
			
			<tr>
				<td colspan="2">
				<td colspan="2">
				<input type="hidden" name="n_no" value="${dto.n_no }">
				<input type="button" id="btnList" value="목록" />
				<input type="button"id="btnUpdate" value="수정" />
				<input type="button" id="btnDelete" value="삭제" />
			
			</tr>	 
		</table>
	</form>
</body>
</html>