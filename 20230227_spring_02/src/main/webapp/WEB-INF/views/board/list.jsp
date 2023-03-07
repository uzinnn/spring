<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>게시판 글목록</h1>
	
	<table>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		
<c:forEach items="${boardlist }" var="board">
		<tr>
			<td>${board.boardNum }</td>
			<td><a href="<%= request.getContextPath() %>/board/read?boardNum=${board.boardNum }">${board.boardTitle }</a></td>
			<td>${board.boardWriter }</td>
			<td>${board.boardDate}</td>
			<td>${board.boardReadcount }</td>
		</tr>

</c:forEach>
</table>

<hr>
	<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
		${page }
		<c:if test="${pageInfo.endPage != page}">
		,	
		</c:if> 
	</c:forEach>
<hr>


</body>
</html>