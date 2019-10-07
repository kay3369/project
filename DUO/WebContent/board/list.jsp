<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Project_Board</title>
	<script  src="${path}/script/jquery-3.4.1.min.js"></script>
	<link rel="stylesheet" href="${path}/board/css/style.css">
	<style>
		#btnWriter{
			text-align : center;
		}
	</style>
	<script>
	$(function(){
		console.log('jQuery ok...');
		
		$('#btnWriter').click(function(){
			console.log('click ok...');
			location.href="${path}/board_servlet/write.do";
		});
		
	});
	</script>
</head>
<body>
	<h2 style="margin-bottom:10px">게시물 목록</h2>
	<button id="btnWriter" name="btnWriter">글쓰기</button>
	<table style = "margin : 0 auto">
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>제목</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr>
		            <td>${dto.num}</td>
					<td>${dto.writer}</td>
					<!-- 나중에 페이징 번호 넣을 곳 -->
					<td>
						<a href="${path}/board_servlet/view.do?num=${dto.num}">${dto.subject}[${dto.comment_count}]</a>
					</td>
					<td>${dto.reg_date}</td>
					<td>${dto.readcount}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" align="center">
				<%-- 	<c:if test="${cur_block > 1}">
								<a href="${path}/board_servlet/list.do?curPage=${prev_page}">[PREVIOUS]</a>
					</c:if> --%> 
					<a>[PREVIOUS]</a>
					<c:forEach var="i" begin="1" end="${totalPage}">
						<a href="${path}/board_servlet/list.do?curPage=${i}">${i}</a>&nbsp;
					</c:forEach>
					<a>[NEXT]</a>
	<%-- 		    	<c:if test="${cur_block < total_block}">
						<a href="${path}/board_servlet/list.do?curPage=${next_page}">[NEXT]</a>
					</c:if>	 --%>
				</td>
			</tr>
		</table>
</body>
</html>