<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="exceptionType" value="${deleteException.class.simpleName}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ����</title>
</head>
<body>
����:
<c:choose>
	<c:when test="${exceptionType == 'ArticleNotFoundException'}">
	������ �Խñ��� �������� �ʽ��ϴ�.
	</c:when>
	<c:when test="${exceptionType == 'InvalidPasswordException'}">
	��ȣ�� �߸� �Է��ϼ̽��ϴ�.
	</c:when>	
</c:choose>
<br>
<a href="<c:url value='list.jsp?p=${param.p}'/>">��Ϻ���</a>
</body>
</html>