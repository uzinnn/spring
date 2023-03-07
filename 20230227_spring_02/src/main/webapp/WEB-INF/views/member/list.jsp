<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>괸리자 - 회원목록</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
<style>
	.f-3{
		display:flex;
		width:300px;
		height:50px;
		
	}
	
	.f-3>div{
		border:1px black solid;
	}
</style>
</head>
<body>

	<div class="f-3">
		<div>아이디</div>
		<div>이름</div>
		<div>이메일</div>
	</div>

	<c:forEach items="${memberlist }" var="member">
		<div class="f-3">
			<a href="<%=request.getContextPath()%>/member/info?id=${member.id }">
			<div>${member.id }</div>
			<div>${member.name }</div>
			<div>${member.email }</div>
			</a>
		</div>
	</c:forEach>
	
</body>
</html>