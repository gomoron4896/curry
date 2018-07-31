<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String idData = request.getParameter("id");
String pwdData = request.getParameter("pwd");
String EMData = request.getParameter("email");
String nameData = request.getParameter("name");
String ageData = request.getParameter("age");
String etcData = request.getParameter("etc");

String texts = "입력받은 ID : " + idData + "<br>";
texts += "입력받은 PWD : " + pwdData + "<br>";
texts += "입력받은 E-MAIL : " + EMData + "<br>";
texts += "입력받은 NAME : " + nameData + "<br>";
texts += "입력받은 AGE : " + ageData + "<br>";
texts += "ETC : " + etcData;

out.println(texts);
%>
</body>
</html>