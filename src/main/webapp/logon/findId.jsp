<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ID ã��</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
	function checkIt(){
		var findForm = eval("document.findForm"); 
		if(!findForm.name.value){
			alert("�̸��� �Է��ϼ���");
			return false;
		}
		
		if(!findForm.jumin1.value || !findForm.jumin2.value){
			alert("�ֹε�Ϲ�ȣ�� �Է��ϼ���");
			return false;
		}
	}
</script>
</head>
<body bgcolor="<%= bodyback_c %>">
<form name="findForm" method="post" action="findIdPro.jsp" onsubmit="return checkIt()">
	<table width="600" align="center" cellspacing="0" cellpadding="5" border="1">
		<tr>
			<td width="200" bgcolor="<%= title_c %>">����� �̸�</td>
			<td width="400">
				<input type="text" name="name" size="15" maxlength="10">			
			</td>
		</tr>
		<tr>
			<td width="200" bgcolor="<%= title_c %>">�ֹε�Ϲ�ȣ</td>
			<td width="400">
				<input type="text" name="jumin1" size="7" maxlength="6">			
				- <input type="text" name="jumin2" size="7" maxlength="7">			
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" name="findid" value="IDã��">
			<input type="button" value="�ڷ�" onclick="javascript:window.location='main.jsp'">
		</tr>
	</table>
</form>
</body>
</html>