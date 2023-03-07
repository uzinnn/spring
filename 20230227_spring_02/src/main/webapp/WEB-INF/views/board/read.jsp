<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글답글</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>${board.boardNum } 게시글</h1>

	<div>
		<p>${board.boardTitle }</p>
	</div>
	<div>
		${board.boardContent }
	</div>
	
	<form id="frmReply">
	<fieldset>	<legend>답글작성</legend>
	<div>제목<input type="text" name="boardTitle"></div>
	<div>내용<input type="text" name="boardContent" ></div>
	<input type="hidden" name="boardNum" value="${board.boardNum }">
	<button type="button" class="btn reply">답글작성</button>
	// <button type="reset" >초기화</button>
	 </fieldset>
	 </form>
	 <hr>
<table border="1">
	<thead>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
	 </thead>
	 
	 <tbody>
		 <c:forEach items="${replyList }" var ="reply">
			<tr>
				<td>${reply.boardNum }</td>
				<td><a href="<%= request.getContextPath() %>/board/read?boardNum=${reply.boardNum }">${reply.boardTitle }</a></td>
				<td>${reply.boardWriter }</td>
				<td>${reply.boardDate}</td>
				<td>${reply.boardReadcount }</td>
			</tr>
		 </c:forEach>
	 </tbody>
</table> 
	 
	 
	
	
	<script>
		$(".btn.reply").on("click", replyClickHandler);
		
		function replyClickHandler(){
			console.log(this); //click이 일어난 엘리먼트가 this(DOM 형태)
			console.log($(this)); //this를 jquery 형태로 변형
			console.log($("#frmReply").serialize());
			$.ajax({
				url:"<%=request.getContextPath()%>/board/insertReplyAjax"
				,type:"post"
				,data: $("#frmReply").serialize() //querystring
				
				, dataType:"json" //result와 연관
				// success에 들어오는 데이터가 json형태일것이고 이것을 js object로 변형해서 result 에 실어준다.
				, success: function(result){
					console.log(result);
					console.log(result[0]);
					console.log(result[0].boardDate);
				//	$("#frmReply").reset(); //작성된글 초기화
					frmReply.reset();	
					if(result.length > 0){
						alert("댓글 작성되었습니다");
					}else{
						alert("댓글 작성되지않았습니다. 다시시도하시오");
					}
					// 답글 부분 화면 업데이트
					displayReply(result);
					console.log(result);
					console.log(result[0]);
					console.log(result[0].boardDate);
					
					
					//<c:forEach items="${replyList }" var ="reply">
					// </c:forEach>
					var htmlVal = '';
					for(i = 0; i< result.length; i++){
						var reply = result[i];
						htmlVal += '<tr>';
						htmlVal += '<td>'+reply.boardNum+'</td>';
						htmlVal += '<td><a href="<%=request.getContextPath()%>/board/read?boardNum='+reply.boardNum+'">'+reply.boardTitle+'</a></td>';
						htmlVal += '<td>'+reply.boardWriter+'</td>';
						htmlVal += '<td>'+reply.boardDate+'</td>';
						htmlVal += '<td>'+reply.boardReadcount+'</td>';
						htmlVal += '</tr>';
					}
					$("tbody").html(htmlVal);
					
				}
				
				,error:function(){
				
				}
				
				
			});
		function displayReply(result){
			
			}
		}
	
	</script>
</body>
</html>