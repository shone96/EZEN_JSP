<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>������ ǥ�����(EL) ����</title>
</head>
<body>
<h3>������ ǥ�����(EL)����</h3>
<p>
�����ڸ� ����� ���� ���尴ü�� ����� ��:
<table border="1">
	<thead>
		<td><b>ǥ����</b></td>
		<td><b>��</b></td>
	</thead>
	<tr>
		<td>\${2 + 5}</td>
		<td>${2 + 5}</td>
	</tr>
	<tr>
		<td>\${4 / 5}</td>
		<td>${4 / 5}</td>
	</tr>
	<tr>
		<td>\${5 div 6}</td>
		<td>${5 div 6}</td>
	</tr>
	<tr>
		<td>\${5 mod 7}</td>
		<td>${5 mod 7}</td>
	</tr>
	<tr>
		<td>\${2 < 3}</td>
		<td>${2 < 3}</td>
	</tr>
	<tr>
		<td>\${2 gt 3}</td>
		<td>${2 gt 3}</td>
	</tr>
	<tr>
		<td>\${3.1 le 3.2}</td>
		<td>${3.1 le 3.2}</td>
	</tr>
	<tr>
		<td>\${(5 > 3) ? 5 : 3}</td>
		<td>${(5 > 3) ? 5 : 3}</td>
	</tr>
	<tr>
		<td>\${header.host}</td>
		<td>${header.host}</td>
	</tr>
	<tr>
		<td>\${header["user-agent"]}</td>
		<td>${header["user-agent"]}</td>
	</tr>
</table>

</body>
</html>