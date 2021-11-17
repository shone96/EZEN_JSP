<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page errorPage = "/nov09/error/viewErrorMessage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>파라미터 출력</title>
</head>
<body>
name 파라미터 값: <%= request.getParameter("name").toUpperCase() %>
</body>
</html>