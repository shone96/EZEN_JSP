<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="util.CookieBox" %>
<%
	response.addCookie(CookieBox.createCookie("name", "ȫ�浿"));
	response.addCookie(CookieBox.createCookie("id", "nov08", "/EZEN/nov08", -1));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>CookieBox ��뿹</title>
</head>
<body>

CookieBox�� ����Ͽ� ��Ű ����

</body>
</html>