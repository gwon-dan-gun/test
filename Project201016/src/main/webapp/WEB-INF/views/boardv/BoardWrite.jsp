<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script >
function boardWrite(){
	boardWriteForm.submit();
}
function boardList() {
	location.href="boardlistpaging";
}
</script>
</head>
<body>
	<h2>BoardWrite.jsp</h2>
	<form action="boardwrite" method="post" name="boardWriteForm"> 
	<label for="bwriter">작성자:</label>
	<input type="text" name="bwriter" id="bwriter" value="${sessionScope.loginId}" readonly>
	<label for="bpassword">비밀번호</label>
	<input type="text" name="bpassword" id="bpassword">
	<label for="btitl">제목 :</label> 
	<input type="text" name="btitle" id="btitle"><br>
	<label for="bcontents">내용 :</label> 
		<textarea name="bcontents" id="bcontents" cols="30" rows="10"  >
				</textarea>
	</form>
	<button onclick="boardWrite()">글작성</button>
	<button onclick="boardList()">글목록</button>




</body>
</html>