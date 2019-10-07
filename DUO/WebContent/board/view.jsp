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
	<script  src="../script/jquery-3.4.1.min.js"></script>
	<script>
		$(function(){
			console.log('jquery ok...!');
			comment_list();	// 댓글 목록 조회
			
		
		// 내용 수정 연결 버튼
		$('#btnEdit').click(function(){
			console.log('btnEdit ok...');
			var passwd = $('#passwd').val();
			if (passwd == ""){
				alert("비밀번호 입력하셨나요?");
				$('#passwd').focus();
				return;
			}
			document.form1.action = "${path}/board_servlet/pass_check.do";
			document.form1.submit();
		});
		
		$('#btnList').click(function(){
			console.log('btnList ok...');
			location.href = "${path}/board_servlet/list.do";
		});
		
		$('#btnSave').click(function(){
			console.log('btnSave ok...!');
			
			var param = "board_num=${dto.num}"+"&writer="+$('#writer').val()+"&content="+$('#content').val();
			
			$.ajax({
				type : "post",
				url : "${path}/board_servlet/comment_add.do",
				data : param,
				success : function(){
					console.log('comment add ok...!');
					
					$('#writer').val("");		// 이름란 clear
					$('#content').val("");	    // 댓글란 clear
					$('#writer').focus;
					
					comment_list();
				}
			});
			console.log(param);
			
		});
		
		function comment_list(){
			console.log('comment_list ok...!')
			
			// var param = "board_num = ${dto.num}"+"&writer="+$('#writer').val()+"&content"+$('#content').val();
			var param = "board_num=${dto.num}";		
		
			$.ajax({
				type    : "get",
				url       : "${path}/board_servlet/comment_reply.do",
				data : param,
				success : function(result){
					console.log('board number success!');
					$('#commentList').html(result);
				}
				
			});
		}
		
		});
	</script>
</head>
<body>
	<form action="" name="form1" method="post">
		<table>
			<tr>
				<td>날짜</td>
				<td>${dto.reg_date }</td>
				<td>조회수</td>
				<td>${dto.readcount }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td colspan="3">${dto.writer }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${dto.subject }</td>
			</tr>
			<tr>
				<td>본문</td>
				<td colspan="3">${dto.content }</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td colspan="3">
					<input type="password" name="passwd" id="passwd"/>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="hidden" name="num" value="${dto.num }"/>
					<input type="button" id="btnEdit" value="수정/삭제"/>
					<!-- 답변 버튼 필요시 여기 넣음 -->
					<input type="button" id="btnList" value="목록가기"/>
				</td>
			</tr>
		</table>
	</form>
	<br>
	
	<table>
		<tr>
			<td><input type="text" id="writer" placeholder="이름"/></td>
			<td rowspan="2">
				<input type="button" id="btnSave" name="btnSave" value="확인"/>
			</td>
		</tr>
		<tr>
			<td>
				<textarea id="content" cols="80" row="5" placeholder="댓글 내용을 입력하세요"></textarea>
			</td>
		</tr>
	</table>
	<br>
	<!-- 댓글 목록 view -->
	<div id="commentList"></div>
</body>
</html>