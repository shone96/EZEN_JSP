<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
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
		<td>글 번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>조회수</td>
	</tr>


<c:choose>
	<c:when test="${listModel.hasArticle == false}"> 
	<tr>
		<td colspan="5">
		게시글이 없습니다.
		</td>
	</tr>
	</c:when>
	<c:otherwise>
		<c:forEach var="article" items="${listModel.articleList}">
		<tr>
			<td>${article.id}</td>
			<td>
			<c:if test="${article.level > 0}"> <%-- 답변글일 경우 --%>
				<c:forEach begin="1" end="${article.level}">-</c:forEach>&gt;
			</c:if>
			<c:set var="query" value="articleId=${article.id}&p=${listModel.requestPage}"/>
			<a href="<c:url value="read.jsp?${query}"/>">
			${article.title}
			</a>
			</td>
			<td>${article.writerName}</td>
			<td>${article.postingDate}</td>
			<td>${article.readCount}</td>
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
			<form action="list.jsp" method="get">
				<select name="searchn">
					<option value="0">작성자</option>
					<option value="1">제목</option>
					<option value="2">내용</option>
				</select>
				<input type="text" name="search" value="${search}"/>
				<input type="submit" class="btn" value="검색"/>			
			</form>
			</td>
		</tr>
	</c:otherwise>
</c:choose>
	<tr>
		<td colspan="5">
			<a href="writeForm.jsp">글쓰기</a>
		</td>
	</tr>
</table>
</body>
</html>