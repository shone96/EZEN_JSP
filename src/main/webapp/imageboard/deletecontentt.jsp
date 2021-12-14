<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="madvirus.gallery.ThemeManager" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%
	String themeId = request.getParameter("id");
	String ref_level = request.getParameter("ref_level");
	System.out.println(themeId);
	System.out.println(ref_level);
	
	ThemeManager deletecontentt = ThemeManager.getInstance();
	deletecontentt.deletecontentt(Integer.parseInt(themeId), Integer.parseInt(ref_level));
	response.sendRedirect("read.jsp?id=" + themeId);
%>

