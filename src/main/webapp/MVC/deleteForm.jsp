<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ���</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_c}">
<center><b>�ۼ���</b>
<br>
<form method="post" name="delform" action="/EZEN/MVC/deletePro.do?pageNum=${pageNum}" onsubmit="return deleteSave()">
<table width="360" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td bgcolor="${value_c}" align="center">
		<b>��й�ȣ�� �Է��� �ּ���.</b>
		</td>		
	</tr>
	<tr heigh="30">		
		<td align="center">��й�ȣ :
		<input type="password" size="10" maxlength="12" name="passwd">
		<input type="hidden" name="num" value="${num}">
		</td>
	</tr>	
	<tr height="30">
		<td bgcolor="${value_c}" align="center">
		<input type="submit" value="�ۻ���">		
		<input type="button" value="�۸��" onclick="document.location.href='/EZEN/MVC/list.do?pageNum=${pageNum}'">
		</td>
	</tr>

</table>
</form>
</center>

</body>
</html>