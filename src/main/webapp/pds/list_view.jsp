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
<title>게시글 목록</title>
</head>
<body>
<table border="1">
	<%--listModel은 list.jsp에서 request객체의 listModel속성에 ArticleListModel 객체를 저장한값 --%>
	<c:if test="${listModel.totalPageCount > 0}">
	<tr>
		<td colspan="5">
		${listModel.startRow}-${listModel.endRow}
		[${listModel.requestPage} / ${listModel.totalPageCount}]
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td>번호</td>
		<td>파일명</td>
		<td>파일크기</td>
		<td>다운로드횟수</td>
		<td>다운로드</td>
	</tr>


<c:choose>
	<c:when test="${listModel.hasPdsItem == false}"> 
	<tr>
		<td colspan="5">
		게시글이 없습니다.
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
			<td><a href="download.jsp?id=${item.id}">다운받기</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5">
			<c:if test="${beginPage > 10}">
				<c:choose>
				<c:when test="${searchn == null || search == null}">
				<a href="<c:url value="list.jsp?p=${beginPage-1}" />">이전</a>
				</c:when>
				<c:otherwise>
				<a href="<c:url value="list.jsp?p=${beginPage-1}&searchn=${searchn}&search=${search}" />">이전</a>
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
						<a href="<c:url value="list.jsp?p=${endPage+1}"/>">다음</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="list.jsp?p=${endPage+1}&searchn=${searchn}&search=${search}"/>">다음</a>
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
					<option value="0">파일명</option>
				</select>
				<input type="text" name="search" value="${search}"/>
				<input type="submit" class="btn" value="검색"/>			
			</form>
		</td>
		<td colspan="2">
			<a href="uploadForm.jsp">파일 첨부</a>
		</td>
	</tr>
</table>
</body>
</html>