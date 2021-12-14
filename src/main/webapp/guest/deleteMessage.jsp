<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page errorPage="errorView.jsp" %>
<%@ page import="service.DeleteMessageService" %>
<%@ page import="service.InvalidMessagePasswordException" %>
<%
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	String password = request.getParameter("password");
	boolean invalidPassword = false;
	try{
		DeleteMessageService deleteService = DeleteMessageService.getInstance();
		deleteService.deleteMessage(messageId, password);
	}catch(InvalidMessagePasswordException ex){
		invalidPassword = true;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �޽��� ������</title>
</head>
<body>
<% if(!invalidPassword) { %>
�޽����� �����Ͽ����ϴ�.
<% }else { %>
�Է��� ��ȣ�� �ùٸ��� �ʽ��ϴ�. ��ȣ�� Ȯ�����ּ���.
<% } %>
<br>
<a href="list.jsp">[��� ����]</a>
</body>
</html>