<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>INFO</title>
</head>
<body>
include전 name 파라미터 값 : <%= request.getParameter("name") %>
<hr>
<!--<br>현재 시간은  입니다.<br>-->
<jsp:include page="body_sub.jsp" flush="false">
	<jsp:param name="name" value="최범균"/>
	<jsp:param name="name" value="최범균1"/>
</jsp:include>
<hr>

include후 name 파라미터 값 : <%= request.getParameter("name") %>

</body>
</html>