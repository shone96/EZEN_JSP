<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="pds.service.ListPdsItemService" %>
<%@ page import="pds.model.PdsItemListModel" %>

<%
	String pageNumberString = request.getParameter("p");
	String searchn = request.getParameter("searchn") == null ? null : request.getParameter("searchn");
	String search = request.getParameter("search") == null ? null : request.getParameter("search");
	
	int pageNumber = 1;
	if(pageNumberString != null && pageNumberString.length() > 0){
		pageNumber = Integer.parseInt(pageNumberString);
	}
	
	ListPdsItemService listService = ListPdsItemService.getInstance();
	PdsItemListModel itemListModel; //페이지 번호에 해당하는 itemListModel 객체를 구함
	
	if(searchn == null || search == null){
		itemListModel = listService.getPdsItemList(pageNumber);
	}else{
		itemListModel = listService.getPdsItemList(pageNumber, Integer.parseInt(searchn), search);
		request.setAttribute("searchn", searchn);
		request.setAttribute("search", search);
	}
		
	request.setAttribute("listModel", itemListModel);
	
	if(itemListModel.getTotalPageCount() > 0){
		int beginPageNumber = (itemListModel.getRequestPage() - 1) / 10 * 10 + 1;
		int endPageNumber = beginPageNumber + 9;
		
		if(endPageNumber > itemListModel.getTotalPageCount()){
			endPageNumber = itemListModel.getTotalPageCount();
		}
		request.setAttribute("beginPage", beginPageNumber);
		request.setAttribute("endPage", endPageNumber);
	}	
%>
<jsp:forward page="list_view.jsp" />
