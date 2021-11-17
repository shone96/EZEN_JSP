<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>include 디렉티브</title>
</head>
<body>
<%
	int number = 10;
%>
<%@ include file="./includee.jspf" %>

공동변수 DATAFOLDER = "<%= dataFolder %>"

</body>
</html>