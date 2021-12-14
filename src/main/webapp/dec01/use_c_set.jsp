<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="ssol.Member" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Member member = new Member();
	HashMap<String, String> pref = new HashMap<String, String>();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:set var="member" value="<%= member %>"/>
<c:set target="${member}" property="name" value="최범균"/>

<c:set var="pref" value="<%= pref %>"/>
<c:set var="favoriateColor" value="#{pref.color}"/>

회원 이름 : ${member.name},
좋아하는 색 : ${favoriateColor}
<br>
<c:set target="${pref}" property="color" value="red"/>

설정 이후 좋아하는 색 : ${favoriateColor}

</body>
</html>