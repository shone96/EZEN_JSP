<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.WriteArticleService" %>
<%@ page import="model.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<jsp:useBean id="writingRequest" class="model.WritingRequest"/>
<jsp:setProperty name="writingRequest" property="*"/>
<%
	Article postedArticle = WriteArticleService.getInstance().write(writingRequest);
	request.setAttribute("postedArticle", postedArticle);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խñ� �ۼ�</title>
</head>
<body>
�Խñ��� ����߽��ϴ�.<br/>
<a href="<c:url value='list.jsp'/>">��Ϻ���</a>
<a href="<c:url value='read.jsp?articleId=${postedArticle.id}'/>">�Խñ� �б�</a>
</body>
</html>