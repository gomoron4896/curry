<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/common.css"/>
</head>
<body>

	<form action="login_ok.jsp" onsubmit="return checkValues()">
		ID : <input type="text" id="id" name="id" required><br>
		PWD : <input type="password" id="pwd" name="pwd" required><br>
		<button>LOGIN</button>
	</form>

	<script>
		function checkValues() {
			var idObj = document.querySelector('#id');
			if (idObj.value.trim().length < 3) {
				alert('SMALL ID LENGTH');
				idObj.value = '';
				idObj.focus();
				return false
			}
			var pwdObj = document.querySelector('#pwd');
			if (idObj.value.trim().length < 3) {
				alert('SMALL PWD LENGTH');
				pwdObj.value = '';
				pwdObj.focus();
				return false;
			}
			return true;

		}
	</script>
</body>
</html>