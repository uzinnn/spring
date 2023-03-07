<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
</head>
<body>
<script>
var msg = "${msg}";
if(msg){
	alert(msg);
}
</script>
	<div>
	
		잠시 오류가 발생하였습니다.
		잠시 후 시도하세요
	</div>
	<a href="<%= request.getContextPath()%>/">메인페이지로 이동</a>
</body>
</html>