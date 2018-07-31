<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>

</style>

<title>Insert title here</title>
</head>
<body>
<form action="/user/join_ok.jsp" onsubmit="return checkData()" method="post">
	<table border="2">
		<tr align="center">
			<td colspan="2">회원 가입</td>
		</tr>
		<tr>
			<td>ID</td>
			<td><input id="signIn" type="text" name="id" minlength="5"
				placeholder="5글자 이상" required></td>
		</tr>
		<tr>
			<td>PWD</td>
			<td><input id="signIn" type="password" name="pwd" minlength="5"
				placeholder="5글자 이상" required></td>
		</tr>
		<tr>
			<td>PWD<br>CHECK</td>
			<td><input id="signIn" type="password" name="pwdchck" minlength="5" value=""
				placeholder="비밀번호 재입력" required></td>
		</tr>
		<tr>
			<td>E-MAIL</td>
			<td><input id="signIn" type="email" name="email"></td>
		</tr>
		<tr>
			<td>AGE</td>
			<td><input id="signIn" type="number" name="age" min="0" max="130" required></td>
		</tr>
		<tr>
			<td>NAME</td>
			<td><input id="signIn" type="text" name="name" minlength="2" maxlength="10" required></td>
		</tr>
		<tr>
			<td>ETC</td>
			<td><textarea id="signIn" maxlength="100"></textarea></td>
		</tr>
		
	</table>
	<button>입력 완료</button>
	</form>

	<script>
	var list = null;
		function queryData() {
			list = document.querySelectorAll('#signIn');
			return list;
		}
		
		function checkData() {
			var dataArr = queryData();
			if(dataArr[0].value.trim().length<5) {
				alert('ID는 반드시 공란을 제외한 5자 이상입니다.');
				dataArr[0].value = '';
				dataArr[0].focus();
				dataArr[0].setAttribute('placeholder','ID 재입력 필요')
				return false;
			}
			
			if(dataArr[1].value.trim().length<5) {
				alert('PWD는 반드시 공란을 제외한 5자 이상입니다.');
				dataArr[1].value = '';
				dataArr[1].focus();
				dataArr[1].setAttribute('placeholder','PWD 재입력 필요')
				return false;
			}
			
			if(dataArr[1].value!=dataArr[2].value) {
				alert('입력한 PWD와 다릅니다');
				dataArr[2].value = '';
				dataArr[2].focus();
				dataArr[2].setAttribute('placeholder','PWD 확인 필요')
				return false;
			}
			
			if(dataArr[5].value.trim().length<2) {
				alert('이름은 반드시 띄어쓰기를 제외한 2자 이상입니다.');
				dataArr[5].value = '';
				dataArr[5].focus();
				dataArr[5].setAttribute('placeholder','NAME 재입력 필요')
				return false;
			}
				return true;	
			}
			
	</script>
	<!--
== 회원가입 
id : 5글자 이상
pwd : 5글자 이상
pwd check : pwd와 동일
emain : email 주소
name : 이름 2글자 이상 10글자 이하
age : 최소 0살에서 최대 130살까지
etc : 비고 100글자 이하
 -->
</body>
</html>