<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="util.CookieBox" %>
<%
	response.addCookie(CookieBox.createCookie("LOGIN", "", "/", 0));
	response.addCookie(CookieBox.createCookie("ID", "", "/", 0));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α׾ƿ�</title>
</head>
<body>
�α׾ƿ��Ͽ����ϴ�.
</body>
</html>