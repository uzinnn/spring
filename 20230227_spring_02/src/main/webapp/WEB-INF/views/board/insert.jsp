<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
board/insert/ jsp

<!-- vo와 이름 맞춰서 적어줘야한다 file upload: enctype-->
<form action="insert" method="post" enctype="multipart/form-data">
	<div>
	<input type="text" name="boardTitle" placeholder="제목">
	<input type="text" name="boardContent" placeholder="내용">
	<!-- file의 경우 name의 vo와 다른 이름으로 해야함 -->
	<input type="file" name="report" placeholder="첨부파일+">
	<button>+</button>
	<button type="submit">게시글등록</button>
	</div>
</form>
</body>
</html>