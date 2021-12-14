<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<c:choose>
	<c:when test="${param.type == 'cafe'}">
		<c:import url="https://www.flickr.com/search">
			<c:param name="w" value="cafe"/>
			<c:param name="q" value="보라매 공원"/>
		</c:import>
	</c:when>
	<c:when test="${param.type == 'blog'}">
		<c:import url="https://www.youtube.com/results">
			<c:param name="search_query" value="blog"/>			
		</c:import>
	</c:when>
	<c:when test="${param.type == 'google'}">
		<c:import url="https://www.google.com/search">
			<c:param name="q" value="covid"/>			
		</c:import>
	</c:when>
	<c:otherwise>
		<c:import url="use_import_tag_help.jsp">
			<c:param name="message" value="선택해주세요"/>
		</c:import>
	</c:otherwise>
</c:choose>
