<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>url 태그</title>
</head>
<body>
<c:url value="http://search.daum.net/search" var="searchUrl">
	<c:param name="w" value="blog"/>
	<c:param name="q" value="박지성"/>
</c:url>

<ul>
	<li>${searchUrl}</li>
	<li><c:url value="/use_if_tag.jsp"></c:url></li>
	<li><c:url value="./use_if_tag.jsp"></c:url></li>
</ul>

</body>
</html>