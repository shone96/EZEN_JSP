<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="javax.servlet.ServletException" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �߻�</title>
</head>
<body>
������ �߻��Ͽ����ϴ�!!<br><br>
���� �޽���: <%= exception.getMessage() %>
<%= exception.printStackTrace() %>
<p>
<%
	Throwable rootCause = null;
	if(exception instanceof ServletException){
		rootCause = ((ServletException)exception).getRootCause();
	}else{
		rootCause = exception.getCause();
	}
	
	if(rootCause != null){
		rootCause.printStackTrace();
		do {	
%>
���� ��ô : <%= rootCause.getMessage() %><br>
<%
			rootCause = rootCause.getCause();
		}while(rootCause != null);
	}
%>
</body>
</html>