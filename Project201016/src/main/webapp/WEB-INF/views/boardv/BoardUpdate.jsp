<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>          
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function boardUpdate() {
		var password= '${updateView.bpassword}';
		var passConfirm =document.getElementById("bpassword").value;
		if(password==passConfirm){
			updateform.submit();
		}else{
			alert('비밀번호가 틀립니다')
		}
	}
	function boardlistpaging() {
		location.href="boardlistpaging";
	}
	
</script>
</head>
<body>
	<h2>BoardUpdate.jsp</h2>
	
	<form action="boardupdateprocess" method="post" name="updateform">
		글번호 : <input type="text" name="bnumber" id="bnumber" value="${updateView.bnumber}" readonly><br>
		작성자 : <input type="text" name="bwriter" id="bwriter" value="${updateView.bwriter}"readonly><br>
		비밀번호:<input type="text" name="bpassword" id="bpassword">
		제목 : <input type="text" name="btitle" id="btitle" value="${updateView.btitle}"><br>
		내용 : <textarea cols="30" rows="10" name="bcontents" id="bcontents">
				${updateView.bcontents}</textarea>			
	</form>
	
	<button onclick="boardUpdate()">수정</button>
	<button onclick="boardlistpaging()">글목록</button>
	 
	
	
	
	
</body>
</html>