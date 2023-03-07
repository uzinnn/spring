<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<script>
	var msg="${alertMsg}";
	if(msg){
		alert(msg);
	}

</script>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="./board/list?searchWord=안녕">한글확인</a>
<form action="./board/list?searchWord=안녕" method="post">
<input type="text" name="searchWord">
<button>한국 포스트 확인</button>
<script>
	var msg = "${msg}";
	if(msg){
		alert(msg);
	}
	var msg2 = "${msg}";
	if(msg2){
		alert(msg2);
	}
	

</script>


</form>
</body>
</html>
