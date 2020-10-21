<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function boardwrite() {
	location.href="boardwriteform"
}
</script>
</head>
<body>
	<h2>BoardList.jsp</h2>
	<table border="1">
	<tr>
		 <th>글번호</th>
		 <th>작성자</th>
		 <th>제목</th>
		 <th>작성일자</th>
		 <th>조회수</th>
     </tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
			<td>${board.bnumber}</td>
			<td>${board.bwriter}</td>
			<td><a href="boardview?bnumber=${board.bnumber}">${board.btitle}</a></td>
			<td>${board.bdate}</td>
			<td>${board.bhits}</td>
			</tr>
		</c:forEach>
	</table>
	<button onclick="boardwrite()">글쓰기</button>
	
</body>
</html>
