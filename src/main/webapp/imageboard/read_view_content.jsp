<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="madvirus.gallery.Theme" %>
<%@ page import="madvirus.gallery.ThemeManager" %>
<%@ page import="madvirus.gallery.ThemeManagerException" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%
	String themeId = request.getParameter("parentId");
	String content = request.getParameter("contentt");
	String writer = request.getParameter("name");
	ThemeManager manager = ThemeManager.getInstance();
	
	int ref_level = manager.selectReflevel(Integer.parseInt(themeId));
	
	int count;
	count = manager.selectContent(Integer.parseInt(themeId));
	
	if(count == 0){
		manager.insertContent(Integer.parseInt(themeId), ref_level ,content, writer);		
	}else{
		ref_level++;
		manager.insertContent(Integer.parseInt(themeId), ref_level ,content, writer);
	}
	
	response.sendRedirect("read.jsp?id=" + themeId);
%>
