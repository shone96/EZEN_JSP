<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="logon.LogonDBBean" %>
<%@ include file="color.jsp" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ID ã��</title>
<link href="style.css" rel="stylesheet" type="text/css">
<%
	String name = request.getParameter("name");
	String jumin1 = request.getParameter("jumin1");
	String jumin2 = request.getParameter("jumin2");	
	
	LogonDBBean manager = LogonDBBean.getInstance();
	String findId = manager.findID(name, jumin1, jumin2);
	
	if(findId != null){
%>
</head>
<body>
<table width="270" border="0" cellspacing="0" cellpadding="5" align="center">
	<tr bgcolor="<%= title_c %>">
		<td height="39">ã���ô� ���̵�� <b><%= findId %></b> �Դϴ�</td>
	</tr>
	<tr bgcolor="<%= value_c %>">
		<td align="center">
		<input type="button" value="Ȯ��" onclick="javascript:window.location='main.jsp'">
		</td>
	</tr>
</table>
<% } else {%>
<table width="270" border="0" cellspacing="0" cellpadding="5" align="center">
	<tr bgcolor="<%= title_c %>">
		<td height="39">ã���ô� ���̵� �����ϴ�.</td>
	</tr>
	<tr bgcolor="<%= value_c %>">
		<td align="center">
		<input type="button" value="Ȯ��" onclick="javascript:window.location='main.jsp'">
		</td>
	</tr>
</table>
<% } %>

</body>
</html>