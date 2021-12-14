<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "No-cache");
	response.setHeader("Cache-Control", "No-store");
	response.setDateHeader("Expires", 1L);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խñ� ���</title>
</head>
<body>
<table border="1">
	<%--listModel�� list.jsp���� request��ü�� listModel�Ӽ��� ArticleListModel ��ü�� �����Ѱ� --%>
	<c:if test="${listModel.totalPageCount > 0}">
	<tr>
		<td colspan="5">
		${listModel.startRow}-${listModel.endRow}
		[${listModel.requestPage} / ${listModel.totalPageCount}]
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td>��ȣ</td>
		<td>���ϸ�</td>
		<td>����ũ��</td>
		<td>�ٿ�ε�Ƚ��</td>
		<td>�ٿ�ε�</td>
	</tr>


<c:choose>
	<c:when test="${listModel.hasPdsItem == false}"> 
	<tr>
		<td colspan="5">
		�Խñ��� �����ϴ�.
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${listModel.pdsItemList}">
		<tr>
			<td>${item.id}</td>
			<td><a href="Content.jsp?id=${item.id}">${item.fileName}</a></td>			
			<td>${item.fileSize}</td>
			<td>${item.downloadCount}</td>
			<td><a href="download.jsp?id=${item.id}">�ٿ�ޱ�</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5">
			<c:if test="${beginPage > 10}">
				<c:choose>
				<c:when test="${searchn == null || search == null}">
				<a href="<c:url value="list.jsp?p=${beginPage-1}" />">����</a>
				</c:when>
				<c:otherwise>
				<a href="<c:url value="list.jsp?p=${beginPage-1}&searchn=${searchn}&search=${search}" />">����</a>
				</c:otherwise>
			</c:choose>
			</c:if>
			<c:choose>
				<c:when test="${searchn == null || search == null}">
					<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
					<c:choose>
						<c:when test="${listModel.requestPage == pno}">
						<a href="<c:url value="list.jsp?p=${pno}" />"><span class="currentPage">[${pno}]</span></a>
						</c:when>
						<c:otherwise>
						<a href="<c:url value="list.jsp?p=${pno}" />">[${pno}]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</c:when>
				<c:otherwise>
				<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
					<c:choose>
						<c:when test="${listModel.requestPage == pno}">
						<a href="<c:url value="list.jsp?p=${pno}&searchn=${searchn}&search=${search}" />"><span class="currentPage">[${pno}]</span></a>
						</c:when>
						<c:otherwise>
						<a href="<c:url value="list.jsp?p=${pno}&searchn=${searchn}&search=${search}" />">[${pno}]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${endPage < listModel.totalPageCount}">
				<c:choose>
					<c:when test="${searchn == null || search == null}">
						<a href="<c:url value="list.jsp?p=${endPage+1}"/>">����</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="list.jsp?p=${endPage+1}&searchn=${searchn}&search=${search}"/>">����</a>
					</c:otherwise>
				</c:choose>
			</c:if>
			</td>
		</tr>
	</c:otherwise>
</c:choose>
	<tr>
		<td colspan="3">
			<form action="list.jsp" method="get">
				<select name="searchn">
					<option value="0">���ϸ�</option>
				</select>
				<input type="text" name="search" value="${search}"/>
				<input type="submit" class="btn" value="�˻�"/>			
			</form>
		</td>
		<td colspan="2">
			<a href="uploadForm.jsp">���� ÷��</a>
		</td>
	</tr>
</table>
</body>
</html>