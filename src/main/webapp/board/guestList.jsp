<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="logon.*" %>
<%@ page import="java.util.List" %>
<%@ include file="color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ����Ʈ</title>
<link href="style.css" rel="stylesheet" type="text/css">
<%
	List guestList = null;
	LogonDBBean glist = LogonDBBean.getInstance();
	guestList = glist.guestLists();
%>
</head>
<body bgcolor="<%= bodyback_c %>">
<table width="800" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr bgcolor="<%= value_c %>">
		<td align="center" width="100">����ڸ�</td>
		<td align="center" width="70">ID</td>
		<td align="center" width="150">�ֹι�ȣ ���ڸ�</td>
		<td align="center" width="240">�̸���</td>
		<td align="center" width="240">��α�</td>		
	</tr>
<%
	for(int i = 0; i < guestList.size(); i++) {
		LogonDataBean guest = (LogonDataBean) guestList.get(i);
%>
	<tr>
		<td align="center" width="100"><%= guest.getName() %></td>
		<td align="center" width="70"><%= guest.getId() %></td>
		<td align="center" width="150"><%= guest.getJumin1() %></td>
		<td align="center" width="240"><%= guest.getEmail() %></td>
		<td align="center" width="240"><%= guest.getBlog() %></td>
	<tr>
<%
	}
%>
	<tr>
		<td colspan="5" align="center"><input type="button" value="�ڷΰ���" onclick="document.location.href='../logon/main.jsp'"></td>
	</tr>
</table>

</body>
</html>