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
include�� name �Ķ���� �� : <%= request.getParameter("name") %>
<hr>
<!--<br>���� �ð���  �Դϴ�.<br>-->
<jsp:include page="body_sub.jsp" flush="false">
	<jsp:param name="name" value="�ֹ���"/>
	<jsp:param name="name" value="�ֹ���1"/>
</jsp:include>
<hr>

include�� name �Ķ���� �� : <%= request.getParameter("name") %>

</body>
</html>