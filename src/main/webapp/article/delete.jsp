<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.DeleteArticleService" %>
<%
	request.setCharacterEncoding("euc-kr");
%>

<jsp:useBean id="deleteRequest" class="model.DeleteRequest"/>
<jsp:setProperty name="deleteRequest" property="*"/>
<%
	String viewPage = null;
	try{
		DeleteArticleService.getInstance().deleteArticle(deleteRequest);
		viewPage = "delete_success.jsp";
	}catch(Exception e){
		request.setAttribute("deleteException", e);
		viewPage = "delete_error.jsp";
	}
%>
<jsp:forward page="<%= viewPage %>"/>
