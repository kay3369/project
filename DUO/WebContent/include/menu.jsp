<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="${path }/notice_servlet/list.do">공지사항</a>
<a href="#">게시판</a>
<a href="${path }/aticle_servlet/list.do">문의사항</a>
<a></a>
</body>
</html>