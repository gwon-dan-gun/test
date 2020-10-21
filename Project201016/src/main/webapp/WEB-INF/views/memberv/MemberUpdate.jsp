<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>MemberUpdate.jsp</h2>
	<form action="memberupdateprocess" method="post">
		아이디 : <input type="text" name="mid" value="${updateView.mid}" readonly> <br>
		비밀번호 : <input type="text" name="mpassword" value="${updateView.mpassword}"> <br>
		전화번호 : <input type="text" name="mphone" value="${updateView.mphone}"> <br>
		이메일 : <input type="text" name="memail" value="${updateView.memail}"> <br>
		
		<input type="submit" value="정보수정">
	
	</form>

</body>
</html>