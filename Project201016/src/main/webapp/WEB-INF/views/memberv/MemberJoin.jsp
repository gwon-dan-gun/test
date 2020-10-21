<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function idOverlap(){
		var inputId = $("#mid").val();
		var checkResult = document.getElementById("checkresult");
		
		
		$.ajax({
			type : "post",
			url : "idoverlap",
			data : {"mid" : inputId},
			dataType : "text",
			success : function(result) {
				if(result=="OK"){
					
					checkResult.style.color = "green";
					checkResult.innerHTML = "사용가능한 ID 입니다.";
				} else {
					
					checkResult.style.color = "red";
					checkResult.innerHTML = "이미 사용중인 ID 입니다.";
				}
			},
			error : function(){
				alert("ajax 실패!!");
			}
		});
	} 
</script>
<script>
function pwdeqFn(){
    console.log("pwdEq 함수 호출");
    var pwd=document.getElementById("mpassword").value;
    var pwdch=document.getElementById("mpasswordch").value;
    var eqMsg=document.getElementById("pwdEq");
    if(pwd==pwdch){
        console.log('비밀번호 일치');
        eqMsg.style.color="green";
        eqMsg.innerHTML="비밀번호 일치";

    }else{
        console.log('비밀번호 불일치');
        eqMsg.style.color="red";
        eqMsg.innerHTML="비밀번호 불일치";

    }
}
function pwdCheck(){

    var exp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
    var pwd=document.getElementById("mpassword").value;
    var pwdch=document.getElementById("pwdch");
    if(pwd.match(exp)){
        pwdch.style.color="green";
        pwdch.innerHTML="비밀번호 형식 맞음";
    }else{
        pwdch.style.color="red";
        pwdch.innerHTML="사용할 수 없은 조합입니다."
    }
}
function phoneck(){
    var exp= /^\d{3}-\d{4}-\d{4}$/;
    var phone = document.getElementById("mphone").value;
    var phck = document.getElementById("phck")
    if(phone.match(exp)){
        phck.style.color="green";
        phck.innerHTML="형식 일치";
    }else{
        phck.style.color="red";
        phck.innerHTML="형식 불일치"
    }
}
function disspper(){
    document.getElementById("phck").innerHTML="";
}
function emailSelFn(emailvalue){
    console.log(emailvalue);
    document.getElementById("memailSel").value=emailvalue;
}
function sample6_execDaumPostcode(){
    new daum.Postcode({
        oncomplete: function(data) {
          
            var addr = '';
            var extraAddr = ''; 
            
            if (data.userSelectedType === 'R') { 
                addr = data.roadAddress;
            } else { 
                addr = data.jibunAddress;
            }

            if(data.userSelectedType === 'R'){
               
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
               
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
              
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

</script>
</head>
<body>
	<h2>MemberJoin.jsp</h2>
	카카오 아이디 : ${kakaoId}
	네이버 아이디 : ${naverId}
	
	<form action="memberjoin" method="post" enctype="multipart/form-data">
		<c:choose>
			<c:when test="${kakaoId ne null}">
				아이디 : <input type="text" name="mid" id="mid" onkeyup="idOverlap()">
				<input type="hidden" name="kakaoId" value="${kakaoId}"><br>
				<span id="checkresult"></span> 
			</c:when>
			<c:when test="${naverId ne null}">
				아이디 : <input type="text" name="mid" id="mid" onkeyup="idOverlap()">
				<input type="hidden" name="naverId" value="${naverId}"><br>
				<span id="checkresult"></span> 
			</c:when>
			<c:otherwise>
				아이디 : <input type="text" name="mid" id="mid" onkeyup="idOverlap()">
				<span id="checkresult"></span> 	
			</c:otherwise>
		</c:choose>
		비밀번호 : <input type="password" name="mpassword" id="mpassword" onkeyup="pwdCheck()">
		<span id="pwdch"></span><br>
		비밀번호 확인:<input type="text" id="mpasswordch" name="mpasswordch" onkeyup="pwdeqFn()">
        <span id="pwdEq"></span><br>
		이름 : <input type="text" name="mname"> <br>
		생년월일 : <input type="date" name="mbirth"> <br>
		이메일 : <input type="text" name="memail" id="memail">
		<input type="text" id="memailSel" name="mamailSel">
		 <select id="emilSel" onchange="emailSelFn(this.value)">
			<option value="">직접입력</option>
			<option value="naver.com">naver.com</option>
			<option value="hanmail.net">hanmail.net</option>
			<option value="gmail.com">gmail.com</option>
		</select> <br>
		주소:<input type="text" name="maddress" id="sample6_postcode" placeholder="우편번호">
            <input type="button" name="maddress" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
            <input type="text" name="maddress" id="sample6_address" placeholder="주소"><br>
            <input type="text" name="maddress" id="sample6_detailAddress" placeholder="상세주소">
            <input type="text" name="maddress" id="sample6_extraAddress" placeholder="참고항목">
            <br>
		전화번호 :<input type="text" name="mphone" id="mphone" onkeyup="phoneck()" onblur="disspper()">
		 <span id="phck"></span><br>
	        프로필사진:<input type="file" name="mfile" id="mfile">	<br>	
		
		
		<input type="submit" value="회원가입">
	
	</form>
	
	
</body>
</html>