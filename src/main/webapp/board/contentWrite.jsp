<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="color.jsp" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�ڸ�Ʈ ��� �ۼ�</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
	function checkcomment(){
		if(document.contentWriteForm.commentt.value == ""){
			alert("�ڸ�Ʈ�� �ۼ����ּ���.");
			document.contentWriteForm.commentt.focus();
			return false;
		}
		if(document.contentWriteForm.commenter.value == ""){
			alert("�ۼ��ڸ� �ۼ����ּ���.");
			document.contentWriteForm.commenter.focus();
			return false;
		}
		if(document.contentWriteForm.passwd.value == ""){
			alert("��й�ȣ�� �ۼ����ּ���.");
			document.contentWriteForm.passwd.focus();
			return false;
		}
	}
</script>
</head>
<body bgcolor="<%= bodyback_c %>">
<form name="contentWriteForm" method="post" action="contentWritePro.jsp" onclick="return checkcomment()">
	<table border="1" align="center">
		<tr bgcolor="<%= value_c %>" align="center">
			<td>�ڸ�Ʈ �ۼ�</td>
			<td colspan="2">
				<textarea name="commentt" rows="6" cols="40"></textarea>
			</td>
			<td align="center">
				�ۼ���<br>
				<input type="text" name="commenter" size="10"><%=  %><br> 
				��й�ȣ<br>
				<input type="password" name="passwd" size="10"><br>
				<input type="submit" value="�ڸ�Ʈ�ޱ�"> 
			</td>
		</tr>
	</table>
</form>

</body>
</html>