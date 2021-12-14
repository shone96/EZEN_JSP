<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="menu" items="${userMenus}">
	${menu.name}<br>
</c:forEach>

<tiles:importAttribute name="adminMenus"/>
<c:forEach var="menu" items="${userMenus}">
	${menu.name}<br>
</c:forEach>