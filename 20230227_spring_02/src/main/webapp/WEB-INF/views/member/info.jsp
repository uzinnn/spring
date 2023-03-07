<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보보기</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>내정보</h1>
<form id="frmInfo" method="get">
		<input value="${membervo.id }" type="text" name="id" readonly="readonly"><br>
		<input value="${membervo.passwd }" type="password"readonly="readonly"><br>
		<input value="${membervo.name }" type="text" readonly="readonly"><br>
		<input value="${membervo.email }" type="text" readonly="readonly"><br>
		<button type="button" onclick="jajvascript:frmSubmit('update');">제출</button>
		<button type="button" onclick="frmSubmit('delete');">탈퇴</button>
		
		<button type="button">수정</button>
		<button type="button">탈퇴</button>
	</form>
	
	
	<script>
	
	${"button"}.get(2).click('update',frmSubmit2);
	${"button"}.eq(3).click('delete',frmSubmit2);
	function frmSubmit2(event){
		console.log(this); //this: click이 발생한 시점에 element
		frmInfo.action = event.data;
		frmInfo.submit();
	}
	
	
	
	function frmSubmit(targetEle){
		console.log(this); //this:window 전체
		//form.action = targetEle; //form태그 하나만
		//	document.getElementsByClassName(".aaa")[0];
		
		//document.querySelector(".aaa.bbb[type=text]").action =targetEle ;//id / class하나만
		//document.querySelectorAll("#forminfo")[0].action =targetEle ; //배열: 여러개가능
		//	document.getElementById("frmInfo").action = targetEle;
		//	$("#frmInfo").attr("action",targetEle);	
		//  frmInfo요소에 action속성을 추가하고 속성값은 targetEle로 받아오는 값으로 한다.
		frmInfo.action = targetEle;
		frmInfo.submit();
		//$("#frmInfo").submit();
		
	}
	
	</script>
</body>
</html>