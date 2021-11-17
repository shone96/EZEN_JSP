<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="util.CookieBox" %>
<%
	response.addCookie(CookieBox.createCookie("name", "홍길동"));
	response.addCookie(CookieBox.createCookie("id", "nov08", "/EZEN/nov08", -1));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>CookieBox 사용예</title>
</head>
<body>

CookieBox를 사용하여 쿠키 생성

</body>
</html>