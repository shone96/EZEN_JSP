<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>choose �±� ���</title>
</head>
<body>
<ul>
	<c:choose>
		<c:when test="${param.name == 'bk'}">
		<li> ����� �̸��� ${param.name }�Դϴ�.</li>
		</c:when>	
		<c:when test="${param.age > 18}">
		<li> ����� 19�� �̻��Դϴ�.</li>
		</c:when>	
	<c:otherwise>
		<li>����� 'bk'�� �ƴϰ� 18�� �̸��Դϴ�.</li>
	</c:otherwise>
	</c:choose>
</ul>

</body>
</html>