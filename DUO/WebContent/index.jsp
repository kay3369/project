<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DUO</title>
<script src="script/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="${path}/css/style.css"/>
<script>
	$(function(){
		console.log("login ok...");
		
		$('#btnLogin').click(function(){
			console.log('btnLogin ok...');
			
			var id = $('#id').val();
			var pwd = $('#pwd').val();
			
			if(id==""){
				alert("아이디를 입력하세요.");
				$('#id').focus();
				return;
			}
			if(pwd==""){
				alert("비밀번호를 입력하세요.");
				$('#pwd').focus();
				return;
			}
			
			document.loginForm.action="${path}/member_servlet/login.do";
			document.loginForm.submit();
		});
	});
</script>
</head>
<body>
<h1>데이트 해 DUO!</h1><hr />
<form action="" name="loginForm" method="post">
	아이디 <input type="text" name="id" id="id" size=10 />
	비밀번호 <input type="password" name="pwd" id="pwd" size=10/>
	<input type="button" id="btnLogin" value="로그인">
	<a href="member/addMember.jsp">회원가입</a>
		<%-- <c:if test="${loginUser.name !=null}">
				<td>안녕하세요. ${loginUser.name }(${loginUser.userid })님 환영합니다!</td>
			 
			</c:if>
	<c:when  test="${session.login != null }">
    <p>${session.id}님 환영합니다!</p>
    <p><a href="logout.do">로그아웃</a>
    </c:when> --%>
</form>
</body>
</html>