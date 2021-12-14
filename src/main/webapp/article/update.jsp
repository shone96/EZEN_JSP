<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.UpdateArticleService" %>
<%@ page import="model.Article" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<jsp:useBean id="updateRequest" class="model.UpdateRequest"/>
<jsp:setProperty name="updateRequest" property="*"/>
<%
	String viewPage = null;
	try{
		Article article = UpdateArticleService.getInstance().update(updateRequest);
		request.setAttribute("updateArticle", article);
		viewPage = "update_success.jsp";
	}catch(Exception ex){
		request.setAttribute("updateException", ex);
		viewPage = "update_error.jsp";
	}
%>
<jsp:forward page="<%= viewPage %>"/>