<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="model.ArticleListModel" %>
<%@ page import="dao.ArticleDao" %>
<%@ page import="service.ListArticleService" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%
	String pageNumberString = request.getParameter("p");
	String searchn = request.getParameter("searchn") == null ? null : request.getParameter("searchn");
	String search = request.getParameter("search") == null ? null : request.getParameter("search");
	
	int pageNumber = 1;
	if(pageNumberString != null && pageNumberString.length() > 0){
		pageNumber = Integer.parseInt(pageNumberString);
	}
	
	ListArticleService listService = ListArticleService.getInstance();
	ArticleListModel articleListModel; //페이지 번호에 해당하는 ArticleListModel 객체를 구함
	
	if(searchn == null || search == null){
		articleListModel = listService.getArticleList(pageNumber);
	}else{
		articleListModel = listService.getArticleList(pageNumber, Integer.parseInt(searchn), search);
		request.setAttribute("searchn", searchn);
		request.setAttribute("search", search);
	}
	
	request.setAttribute("listModel", articleListModel);
	
	if(articleListModel.getTotalPageCount() > 0){
		int beginPageNumber = (articleListModel.getRequestPage() - 1) / 10 * 10 + 1;
		int endPageNumber = beginPageNumber + 9;
		
		if(endPageNumber > articleListModel.getTotalPageCount()){
			endPageNumber = articleListModel.getTotalPageCount();
		}
		request.setAttribute("beginPage", beginPageNumber);
		request.setAttribute("endPage", endPageNumber);
	}	
%>
<jsp:forward page="list_view.jsp" />
