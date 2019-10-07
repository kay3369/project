<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path" value="${pageContext.request.contextPath }"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../script/jquery-3.4.1.min.js"></script>

<title>회원가입</title>

<script>
		$(function(){
			console.log("jquery ok...");
			
			$('#btnAdd').click(function(){
				console.log("btnAdd ok");
				
				var id = $('#id').val();
				var pwd = $('#pwd').val();
				var pwd_check = $('#pwd_check').val();
				var nick_name = $('#nick_name').val();
				
				if(id == ""){
					alert("아이디를 입력하세요.");
					$('#id').focus();
					return;
				}
				if(pwd == ""){
					alert("비밀번호를 입력하세요.");
					$('#pwd').focus();
					return;
				}
				if(pwd_check == ""){
					alert("비밀번호 확인란을 입력하세요.");
					$('#pwd_check').focus();
					return;
				}
				if(nick_name == ""){
					alert("닉네임을 입력하세요.");
					$('#nick_name').focus();
					return;
				}
				
				document.addMemberForm.action="${path}/member_servlet/addMember.do";
				document.addMemberForm.submit();
			});
			$('#btnCancel').click(function(){
				console.log("btnCancel ok");
				location.href = "${path}/index.jsp";
			});
		});
		
		
	</script>
</head>
<body>
<%@ include file="../include/header.jsp" %>
	<h2>회원 가입</h2>

	<center>
<form action="" name="addMemberForm" method="post" enctype="multipart/form-data" >
		'*' 표시 항목은 필수 입력 항목입니다.
	<table>
		<tr>
			<td>*아이디</td>
			<td>
			<input type="text" name="id" id="id" size="20"/>
			<input type="hidden" name="reid" id="reid" size="10"/>
			<input type="button" value="중복확인"  id="" onclick="return idCheck()"/>
			</td>
		</tr>
		<tr>
			<td>*비밀번호</td>
			<td><input type="password" name="pwd" id="pwd" size="20"></td>
		</tr>
		<tr>
			<td>*비밀번호 확인</td>
			<td><input type="password" name="pwd_check" id="pwd_check" size="20"></td>
		</tr>
		<tr>
			<td>*닉네임</td>
			<td>
			<input type="text" name="nick_name" id="nick_name" size="20"/>
			<input type="hidden" name="renick_name" id="renick_name" size="20"/>
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>
			<input type="text" name="email1" id="email1" size=20> @
			<select name="email2" id="email2">
				<option value="네이버">naver.com</option>
								<option value="다음">hanmail.com</option>
				<option value="구글">google.com</option>
				<option value="직접입력">직접입력</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
			<input type="text" name="address" id="address">
			<input type="button" name="findAddr" value="주소검색">
			</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
			<input type="radio" name="gender" value="남" />남
			<input type="radio" name="gender" value="여"/>여
			</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><input type="text" name="birth" id="birth"></td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td>
			<input type="text" name="phone1" id="phone1" size="3"> -
			<input type="text" name="phone2" id="phone2" size="4"> -
			<input type="text" name="phone3" id="phone3" size="4">
			</td>
		</tr>
		<tr>
			<td>프로필사진</td>
			<td><input type="file" name="photo" id="photo"></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="button" id="btnAdd" value="가입"/>
			<input type="reset" id="btnCancel" name='btnCancel' value="취소"/>
			</td>
		</tr>
	</table>

</form>
</center>
</body>
</html>