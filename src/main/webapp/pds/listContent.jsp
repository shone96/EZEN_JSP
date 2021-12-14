<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "No-cache");
	response.setHeader("Cache-Control", "No-store");
	response.setDateHeader("Expires", 1L);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 상세보기</title>
</head>
<body>
<table border="1">
	<tr>
		<td>파일명</td>
		<td>${item.fileName}</td>
	</tr>
</table>

</body>
</html>