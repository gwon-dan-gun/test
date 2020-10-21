<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function boardWrite(){
	boardWriteForm.submit();
}
	function boardList() {
		location.href = "boardlistpaging";
	}
</script>
</head>
<body>
<h2>BoardWriteFile.jsp</h2>
	<form action="boardwritefile" method="post" name="boardWriteForm" enctype="multipart/form-data"> 
	작성자:
	<input type="text" name="bwriter" id="bwriter" value="${sessionScope.loginId}" readonly><br>
	제목 :
	<input type="text" name="btitle" id="btitle"><br>
	비밀번호:
	<input type="text" name="bpassword" id="bpassword"><br>
	내용 :
	<textarea name="bcontents" id="bcontents" cols="30" rows="10"></textarea>
	참부파일:
	<input type="file" name="bfile" id="bfile">			
	</form>
	<button class="btn btn-sm btn-primary" onclick="boardWrite()">글작성</button>
	<button class="btn btn-sm btn-primary" onclick="boardList()">글목록</button>


</body>
</html>