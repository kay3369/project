<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
      <c:set var="path" value="${pageContext.request.contextPath }"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<c:url value="./js/jquery-3.4.1.min.js"/>"></script>
<title>Insert title here</title>
</head>
<body>
<%@include file="../include/header.jsp" %>
<%@include file="../include/menu.jsp" %>

	 
	<table>
	
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>조회수</td>
			
		</tr>
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.rn}</td>
			<td>${dto.n_writer }</td>
			<td><a href="${path }/notice_servlet/read.do?n_no=${dto.n_no}">${dto.n_title }</a></td>
			<td>${dto.n_date }</td>
			<td>${dto.n_count }</td>
			
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
			<c:if test="${curBlock>1 }">
				<a href=" ${path }/notice_servlet/list.do?curPage=1">[<<]</a>
				<a href="${path }/notice_servlet/list.do?curPage=${prev_page}">[이전]</a>	
			</c:if>
			<c:forEach var="i" begin="${blockStart }" end="${blockEnd }">
					<a href="${path }/notice_servlet/list.do?curPage=${i }">${i }</a>&nbsp;
				</c:forEach>
				
				<c:if test="${curBlock<totBlock }">
			<a href="${path }/notice_servlet/list.do?curPage=${next_page}">[다음]</a>	
			</c:if>
			<a href=" ${path }/notice_servlet/list.do?curPage=${totPage}">[>>]</a>
			
			</td>
		</tr>
	</table>
	<%@include file="../include/footer.jsp" %>
</body>
</html>