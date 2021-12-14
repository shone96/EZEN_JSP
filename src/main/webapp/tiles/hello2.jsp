<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%
	request.setAttribute("greeting", "환영합니다.");
%>
<tiles:insertDefinition name="hello2"/>