<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h2>home.jsp</h2>
	
	<button onclick="location.href='memberloginform'">로그인</button>
	<button onclick="location.href='memberjoinform'">회원가입</button>
	
	
	<h3>카카오로 회원가입</h3>
	<a href="kakaojoin"> <img
		src="${pageContext.request.contextPath}/resources/UploadFile/kakao_login_medium_narrow.png" />
	</a>
	<br>

	<h3>네이버로 회원가입</h3>
	<a href="naverjoin"> <img
		src="${pageContext.request.contextPath}/resources/UploadFile/네이버 아이디로 로그인_완성형_Green.PNG" />
	</a>
	<br>
</body>
</html>
