<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>if �±�</title>
</head>
<body>
<c:if test="true">
������ ����<br>
</c:if>

<c:if test="${param.name == 'bk'}">
name �Ķ���� ���� ${param.name} �Դϴ�.<br>
</c:if>

<c:if test="${18 < param.age}">
����� ���̴� 18�� �̻��Դϴ�.<br>
</c:if>

</body>
</html>