<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ���</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>

<body bgcolor="${bodyback_c}">
<center><b>�۳��� ����</b><br>
	<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="${bodyback_c}" align="center">
		<form>			
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c}">�۹�ȣ</td>
				<td align="center" width="125">${article.num}</td>
				<td align="center" width="125" bgcolor="${value_c}">��ȸ��</td>
				<td align="center" width="125">${article.readcount}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c}">�ۼ���</td>
				<td align="center" width="125" align="center">${article.writer}</td>
				<td align="center" width="125" bgcolor="${value_c}">�ۼ���</td>
				<td align="center" width="125" align="center">${article.reg_date}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c}">������</td>
				<td align="center" width="375" colspan="3">${article.subject}</td>				
			</tr>
			<tr>
				<td align="center" width="125" bgcolor="${value_c}">�۳���</td>
				<td align="center" width="375" colspan="3">${article.content}</td>
			</tr>
			<tr height="30">
				<td colspan=4 bgcolor="${value_c}" align="right">
				<input type="button" value="�ۼ���" onclick="document.location.href='/EZEN/MVC/updateForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="�ۻ���" onclick="document.location.href='/EZEN/MVC/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="��۾���" onclick="document.location.href='/EZEN/MVC/writeForm.do?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="�۸��" onclick="document.location.href='/EZEN/MVC/list.do?&pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
		</form>		
</center>
</body>
</html>