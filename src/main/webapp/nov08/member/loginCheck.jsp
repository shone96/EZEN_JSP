<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="util.CookieBox" %>
<%
	CookieBox cookieBox = new CookieBox(request);
	boolean login = cookieBox.exists("LOGIN") && cookieBox.getValue("LOGIN").equals("SUCCESS");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��ο��� �˻�</title>
</head>
<body>
<%
	if(login){
		
%>
���̵� "<%= cookieBox.getValue("ID") %>"�� �α��� �� ����
<%
	} else {
%>
�α������� ���� ����
<%
	}
%>

</body>
</html>