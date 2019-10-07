<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project_Board</title>
<script  src="../script/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="${path}/board/css/style.css">
<style>
	h2{
		text-align : center;
		padding : 20px 0;
	}
	.button{
		text-align : center;
		padding : 10px 0;
	}
	.row{
		text-align : center;
		padding : 5px 0;
	}
</style>
<script>
	$(function(){
		$('#btnSave').click(function(){
			var writer = $('#writer').val();
			var subject = $('#subject').val();
			var content = $('#content').val();
			var passwd = $('#passwd').val();
			
			if (writer == ""){
				alert("이름을 입력하세요");
				$('#writer').focus();
				return;
			}
			if (subject == ""){
				alert("제목을 입력하세요");
				$('#subject').focus();
				return;
			}
			if (content == ""){
				alert("내용을 입력하세요");
				$('#content').focus();
				return;
			}
			if (passwd == ""){
				alert("비밀번호를 입력하세요");
				$('#passwd').focus();
				return;
			}
			
			// post 방식 넘길 때
			document.form1.action = "${path}/board_servlet/insert.do",
			document.form1.submit();
		});
		
		// Q. 글쓰기를 취소하면 reset이 아니고 목록에 가는게 맞는 것인가??
				$('#btnList').click(function(){
					// console.log('글쓰기 목록가기');
					location.href = "${path}/board_servlet/list.do";	// get방식 넘길 때 
				});
	});
</script>
</head>
<body>
<h2>게시판 글쓰기</h2>
<form action="" name="form1" method="post" >
	<table style="margin:0 auto;">
		<tr>
			<td class="row">이름</td>
			<td><input type="text" name="writer" id="writer" /></td>
		</tr>
		<tr>
			<td class="row">제목</td>
			<td><input type="text" name="subject" id="subject" /></td>
		</tr>
		<tr>
			<td class="row">내용</td>
			<td>
				<textarea name="content" id="content" cols="60" rows="5"></textarea>
			</td>
		</tr>
		<tr>
			<td class="row">비밀번호</td>
			<td><input type="password" name="passwd" id="passwd" /></td>
		</tr>
		<tr>
			<td colspan="2" class="button">
				<input type="button" name="btnSave" id="btnSave" value="확인" />
				<input type="button" name="btnList" id="btnList" value="취소"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>