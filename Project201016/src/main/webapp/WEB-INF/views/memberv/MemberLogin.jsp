<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>MemberLogin.jsp</h2>
	
	<form action="memberlogin" method="post">
		아이디 : <input type="text" name="mid"> <br>
		비밀번호 : <input type="text" name="mpassword"> <br>
		<input type="submit" value="로그인">
	</form>
	
	<h3>카카오로 로그인</h3>
	<a href="kakaologin">
		<img src="${pageContext.request.contextPath}/resources/UploadFile/kakao_login_medium_narrow.png"/>
	</a><br>
	
	<h3>네이버로 로그인</h3>
	<a href="naverlogin">
		<img src="${pageContext.request.contextPath}/resources/UploadFile/네이버 아이디로 로그인_완성형_Green.PNG"/>
	</a><br>
	

</body>
</html>