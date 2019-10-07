<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path" value="${pageContext.request.contextPath }"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script src="../script/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		console.log("jquery ok");
		
		$('#btnSave').click(function(){
			var writer =$('#n_writer').val();
			var title =$('#n_title').val();
			var detail =$('#n_detail').val();
			
			
			if(writer ==""){
				alert("이름을 입력하시오");
				$('#n_writer').focus();
				return;
			}
			if(subject ==""){
				alert("제목을 입력하시오");
				$('#n_title').focus();
				return;
			}if( content==""){
				alert("내용을 입력하시오");
				$('#n_detail').focus();
				return;
			}
			document.form1.action="${path}/notice_servlet/insert.do";
			document.form1.submit();
		});
		
		$('#btnList').click(function(){
			location.href="${path}/notice_servlet/list.do";
			
		});
	});
</script>
</head>
<body>
	<h2>공지사항</h2> <hr />
		<form action="" name="form1" method="post" >
		<table>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="n_writer" id="writer" /> </td> <!-- 되도록이면 DB랑 이름똑같이  DTO도  -->
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="n_title"  /> </td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="n_detail" id="content" cols="60" row="5" ></textarea> </td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" id="btnSave" value="확인" />
				<input type="reset" value="취소" id="btnList"/>  </td>
			</tr>	 
		</table>
	</form>

</body>
</html>