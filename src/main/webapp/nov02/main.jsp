<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�α���</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrap">
	<form action="inputPro.jsp" name="serinput" onsubmit="return checkIt()" method="post">
		<h1>�ڷα��Ρ�</h1>
		<div id="body">
			<table width="500px; height=500px">
				<colgroup>
					<col width="20%" height="20%"/> 
					<col width="*" /> 
				</colgroup>
				<tr>
					<th colspan="2" class="subTitle">*���̵� �Է�</th>
				</tr>
				<tr>
					<th rowspan="1">����� ID</th>
					<td>
						<input type="text" name="id" maxlength="12" />					
					</td>
				</tr>
				<tr>
					<th rowspan="1">��й�ȣ</th>
					<td><input type="password" name="passwd2" maxlength="12" /></td>
				</tr>				
			</table>
		</div>
		<div id="footer">
			<input type="submit" name="confirm" class="inputByn" value="�α���" />
			<input type="button" class="inputByn" value="ȸ������" onclick="javascript:window.location='inputForm.jsp'" />
		</div>		
	</form>
</div>
</body>
</html>