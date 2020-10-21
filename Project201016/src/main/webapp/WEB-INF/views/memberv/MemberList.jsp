<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	function memberViewAjax(mid) {
		console.log(mid);
		$.ajax({
			type : "post",
			url : "memberviewajax",
			data : {
				"mid" : mid
			},
			dataType : "json",
			success : function(result) {
				console.log(result);
				console.log(result.mid);

				var output = "<table border='1'>";
				output += "<tr><th>ID</th> <th>PASSWORD</th> <th>NAME</th>";
				output += "<th>PHONE</th> <th>EMAIL</th> <th>BIRTH</th></tr>";
				output += "<tr>";
				output += "<td>" + result.mid + "</td>";
				output += "<td>" + result.mpassword + "</td>";
				output += "<td>" + result.mname + "</td>";
				output += "<td>" + result.mphone + "</td>";
				output += "<td>" + result.memail + "</td>";
				output += "<td>" + result.mbirth + "</td>";
				output += "</tr>";
				output += "</table>";

				$("#memberviewdiv").html(output);
			},
			error : function() {
				console.log("통신실패");
			}
		});
	}
</script>
</head>
<body>
	<h2>MemberList.jsp</h2>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>PASSWORD</th>
				<th>이름</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>주소</th>
				<th>전화번호</th>
				<th>상세조회</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${memberList}">
				<tr>
					<td>${member.mid}</td>
					<td>${member.mpassword}</td>
					<td>${member.mname}</td>
					<td>${member.mbirth}</td>
					<td>${member.memail}</td>
					<td>${member.maddress}</td>
					<td>${member.mphone}</td>
                     
                    <td><a href="memberview?mid=${member.mid}">조회</a></td>
					<td><button onclick="memberViewAjax('${member.mid}')">조회(ajax)</button></td>
					<td><a href="memberdelete?mid=${member.mid}">삭제</a></td>
				</tr>
				<br>
			</c:forEach>
		</tbody>
	</table>
	<button onclick="location.href='membermain'">마이페이지</button>

	<div id="memberviewdiv"></div>

</body>
</html>