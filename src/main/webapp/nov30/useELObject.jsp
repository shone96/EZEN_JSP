<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
	request.setAttribute("name", "�ֹ���");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>EL Object</title>
</head>
<body>
��û URL: ${pageContext.request.requestURI}<br>
request�� name �Ӽ�: ${requestScope.name}<br>
code �Ķ����: ${param.code}

</body>
</html>