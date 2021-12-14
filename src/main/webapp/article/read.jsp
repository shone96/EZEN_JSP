<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.ReadArticleService" %>
<%@ page import="service.ArticleNotFoundException" %>
<%@ page import="model.Article" %>
<%
	int articleId = Integer.parseInt(request.getParameter("articleId"));
	String viewPage = null;
	try{
		Article article = ReadArticleService.getInstance().readArticle(articleId); //ReadArticleService Ŭ������ readArticle�޼ҵ忡 articleId���� �־ �Խñ��ϳ� ������
		request.setAttribute("article", article);
		viewPage = "read_view.jsp";
	}catch(ArticleNotFoundException ex){
		viewPage = "article_not_found.jsp";
	}
%>
<jsp:forward page="<%= viewPage %>"/>