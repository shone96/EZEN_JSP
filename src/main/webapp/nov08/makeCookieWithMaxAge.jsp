<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   
<%
	Cookie cookie = new Cookie("one", "10s");
	cookie.setMaxAge(10);
	response.addCookie(cookie);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��Ű��ȿ �ð�����</title>
</head>
<body>

��ȿ �ð��� 10���� one ��Ű ����.

</body>
</html>