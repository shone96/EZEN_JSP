<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>timeZone ?±? ????</title>
</head>
<body>

<c:set var="now" value="<%= new java.util.Date() %>"/>

<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
<br>
<fmt:timeZone value="Hongkong">
<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
</fmt:timeZone>

</body>
</html>