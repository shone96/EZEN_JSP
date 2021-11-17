<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.Date" %>
<%
	Date now = new Date();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>현재 시간</title>
</head>
<body>
	현재 시간 : 
	<%= now %>
</body>
</html>