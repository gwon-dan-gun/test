<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>MemberView.jsp</h2>
	<table border="1">
		<tr>
		<th>ID</th> <th>PASSWORD</th> <th>이름</th>
		<th>생년월일</th> <th>주소</th> <th>전화번호</th>
		<th>프로필사진</th></tr>
		<tr>
			<td>${memberView.mid}</td>
			<td>${memberView.mpassword}</td>
			<td>${memberView.mname}</td>
			<td>${memberView.mbirth}</td>
			<td>${memberView.maddress}</td>
			<td>${memberView.mphone}</td>
			<td>>${memberView.mfilename}</td>
			 </tr>
	
	</table>
	<button onclick="location.href='memberlist'">목록</button>

</body>
</html>