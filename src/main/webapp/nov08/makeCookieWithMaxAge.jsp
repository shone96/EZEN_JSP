<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   
<%
	Cookie cookie = new Cookie("one", "10s");
	cookie.setMaxAge(10);
	response.addCookie(cookie);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>쿠키유효 시간설정</title>
</head>
<body>

유효 시간이 10초인 one 쿠키 생성.

</body>
</html>