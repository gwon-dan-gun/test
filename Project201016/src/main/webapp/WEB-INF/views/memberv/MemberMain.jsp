<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function memberUpdate(){
		location.href="memberupdate";
	}
	function logout(){
		location.href="memberlogout";
	}
	function boardwrite() {
		location.href="boardwriteform"
	}
	function boardWriteFile() {
		location.href = 'boardwritefileform';
	}
	function boardlistpaging(){
		location.href="boardlistpaging"
	}
</script>

</head>
<body>
	<h2>MemberMain.jsp</h2>
	
	<h2>${sessionScope.loginId} 님 환영합니다.</h2>
	<c:if test="${sessionScope.loginId eq 'admin'}">
		<a href="memberlist">회원목록 조회</a><br>
	</c:if>
	<button onclick="memberUpdate()">정보수정</button><br>
	<button onclick="logout()">로그아웃</button><br>
	
	<h2>게시판</h2>
	<button onclick="boardlistpaging()">글목록</button><br>
	<button onclick="boardwrite()">글쓰기</button><br>
	<button onclick="boardWriteFile()">글쓰기(파일첨부)</button><br>

</body>
</html>