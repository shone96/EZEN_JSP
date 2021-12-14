<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="exceptionType" value="${replyException.class.simpleName}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�亯 ����</title>
</head>
<body>
����:
<c:choose>
	<c:when test="${exceptionType == 'ArticleNotFoundException'}">
	�亯�� ����� �Խñ��� �������� �ʽ��ϴ�.
	</c:when>
	<c:when test="${exceptionType == 'CannotReplyArticleException'}">
	�亯 ���� ����� �� ���� �Խñ��Դϴ�
	</c:when>
	<c:when test="${exceptionType == 'LastChildAleadyExistsException'}">
	����� �� �ִ� �亯 ������ �ʰ��߽��ϴ�.
	</c:when>
</c:choose>
<br>
<a href="<c:url value='list.jsp?p=${param.p}'/>">��Ϻ���</a>
</body>
</html>