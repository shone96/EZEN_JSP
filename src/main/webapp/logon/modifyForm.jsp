<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="logon.*" %>
<%@ include file="color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ����������</title>
<link href="style.css" rel="stylesheet" type="text.css">
<script language="javascript">

	function checkIt(){
		var userinput = eval("document.userinput"); //userinput �� ������ ������
				
		if(!userinput.passwd.value){
			alert("��й�ȣ�� �Է��ϼ���");
			return false;
		}
		if(userinput.passwd.value != userinput.passwd2.value){
			alert("��й�ȣ�� �����ϰ� �Է��ϼ���");
			return false;
		}
		if(!userinput.name.value){
			alert("����� �̸��� �Է��ϼ���");
			return false;
		}
		if(!userinput.jumin1.value || !userinput.jumin2.value){
			alert("�ֹε�Ϲ�ȣ�� �Է��ϼ���");
			return false;
		}
	}
</script>
<%
	String id = (String)session.getAttribute("memId");

	LogonDBBean manager = LogonDBBean.getInstance();
	LogonDataBean c = manager.getMember(id);
	
	try{
%>
</head>
<body bgcolor="<%= bodyback_c %>">
<form method="post" action="modifyPro.jsp" name="userinput" onsubmit="return checkIt()">
	<table width="600" border="1" cellspacing="0" cellpadding="3" align="center">
		<tr>
			<td colspan="2" height="39" bgcolor="<%= title_c %>" align="center">
			<font size="+1"><b>ȸ�� ��������</b></font>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="normal" align="center">ȸ���� ������ �����մϴ�.</td>
		</tr>
		<tr>
			<td width="200" bgcolor="<%= value_c %>"><b>���̵��Է�</b></td>
			<td width="400" bgcolor="<%= value_c %>"></td>
		</tr>
		<tr>
			<td width="200">����� ID</td>
			<td width="400"><%= c.getId() %></td>
		</tr>
		<tr>
			<td width="200">��й�ȣ</td>
			<td width="400"><input type="password" name="passwd" size="10" maxlength="10" value="<%= c.getPasswd() %>"></td>
		</tr>
		<tr>
			<td width="200" bgcolor="<%= value_c %>"><b>�������� �Է�</b></td>
			<td width="400" bgcolor="<%= value_c %>"></td>
		</tr>
		<tr>
			<td width="200">����� �̸�</td>
			<td width="400"><input type="text" name="name" size="15" maxlength="20" value="<%= c.getName() %>"></td>
		</tr>
		<tr>
			<td width="200">�ֹε�Ϲ�ȣ</td>
			<td width="400"><%= c.getJumin1() %> - <%= c.getJumin2() %></td>
		</tr>
		<tr>
			<td width="200">E-Mail</td>
			<td width="400"><%if(c.getEmail() == null) { %>
				<input type="text" name="email" size="40" maxlength="30">
				<% }else{ %>
				<input type="text" name="email" size="40" maxlength="30" value="<%= c.getEmail() %>">
				<% } %>
			</td>
		</tr>
		<tr>
			<td width="200">Blog</td>
			<td width="400"><%if(c.getBlog() == null) { %>
				<input type="text" name="blog" size="60" maxlength="50">
				<% }else{ %>
				<input type="text" name="blog" size="60" maxlength="50" value="<%= c.getBlog() %>">
				<% } %>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" bgcolor="<%= value_c %>">
			<input type="submit" name="modify" value="����">
			<input type="button" value="���" onclick="javascript:window.location='main.jsp'">
		</tr>
	</table>
</form>

</body>
<% } catch(Exception e) {}%>
</html>