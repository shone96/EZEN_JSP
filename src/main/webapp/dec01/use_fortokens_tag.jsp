<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>fortokens �±�</title>
</head>
<body>
�޸��� ���� �����ڷ� ��� : <br>
<c:forTokens var="token" items="������,�����,��Ȳ��.�Ķ���.������" delims=",.">
	${token}
</c:forTokens>

</body>
</html>