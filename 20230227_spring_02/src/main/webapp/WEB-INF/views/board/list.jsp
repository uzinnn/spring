<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<hr>

	<c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage}" >
		${page }
		<c:if test="${pageInfo.endPage != page}">
		,
		</c:if>
	</c:forEach>

</body>
</html>