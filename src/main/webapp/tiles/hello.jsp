<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%
	request.setAttribute("greeting", "¾È³çÇÏ¼¼¿ä");
%>
<tiles:insertDefinition name="hello"/>