<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ǥ������� ��뿹��2</title>
</head>
<body>
<h3>ǥ������� ��뿹��2 -�Ķ���Ͱ� ó��</h3>
<p>
<form action="eLEx2.jsp" method="post">
�̸� : <input type="text" name="name" value="${param.name}">
	  <input type="submit" value="Ȯ��">
</form>
<p>
�̸���: ${param.name} �Դϴ�.

</body>
</html>