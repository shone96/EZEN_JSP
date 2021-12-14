<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="exceptionType" value="${replyException.class.simpleName}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>답변 실패</title>
</head>
<body>
에러:
<c:choose>
	<c:when test="${exceptionType == 'ArticleNotFoundException'}">
	답변을 등록할 게시글이 존재하지 않습니다.
	</c:when>
	<c:when test="${exceptionType == 'CannotReplyArticleException'}">
	답변 글을 등록할 수 없는 게시글입니다
	</c:when>
	<c:when test="${exceptionType == 'LastChildAleadyExistsException'}">
	등록할 수 있는 답변 개수를 초과했습니다.
	</c:when>
</c:choose>
<br>
<a href="<c:url value='list.jsp?p=${param.p}'/>">목록보기</a>
</body>
</html>