<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="madvirus.gallery.Theme" %>
<%@ page import="madvirus.gallery.ThemeManager" %>
<%@ page import="madvirus.gallery.ThemeManagerException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String themeId = request.getParameter("id");

	ThemeManager manager = ThemeManager.getInstance();
	Theme theme = manager.select(Integer.parseInt(themeId));	
	
	int count;
	count = manager.selectContent(Integer.parseInt(themeId));
	
%>
<c:set var="theme" value="<%= theme %>"/>
<c:if test="${empty theme}">
존재하지 않는 테마 이미지입니다.
</c:if>
<c:if test="${! empty theme}">
<table width="100%" border="1" cellpadding="1" cellspacing="0">
	<tr>
		<td>제목</td>
		<td>${theme.title}</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${theme.name}
		<c:if test="${empty theme.email}">
		<a href="mailto:${theme.email}">[이메일]</a>
		</c:if>
		</td>
	</tr>
	<c:if test="${! empty theme.image}">
	<tr>
		<td colspan="2" align="center">
		<a href="javascript:viewLarge('/EZEN/image/${theme.image}')">
		<img src="/EZEN/image/${theme.image}" width="150" border="0">
		<br>[크게보기]
		</a>
	</tr>
	</c:if>
	<tr>
		<td>내용</td>
		<td>${theme.content}</td>
	</tr>
	<tr>
		<td colspan="2">
		<a href="javascript:goReply()">[답변]</a>
		<a href="javascript:goModify()">[수정]</a>
		<a href="javascript:goDelete()">[삭제]</a>
		<a href="javascript:goList()">[목록]</a>
	</tr>
	<table border="1" align="center" width="100%">
	<form name="content" action="read_view_content.jsp" method="post">
		<input type="hidden" name="parentId" value="<%= themeId %>">
		<tr align="center" height="100">
			<td><b align="center">코멘트 작성</b></td>
			<td rowspan="3">
			<textarea name="contentt" rows="7" cols="40"></textarea>
			</td>
			<td align="center">
			작성자<br>
			<input type="text" name="name" size="10"><br>	
			비밀번호<br>
			<input type="password" name="name" size="10"><br>
			<input type="submit" value="코멘트달기">			
			</td>
		</tr>
	</form>
	</table>
	<%if(count > 0) {
	%>
	<table width="100%" border="1" cellpadding="1" cellspacing="0" align="center">
		<%		
			for(int i = 0; i < count; i++) {
				Theme themeContent = manager.selectContentt(Integer.parseInt(themeId), i);
				
		%>
				<tr align="center">
					<td>작성자</td>
					<td>내용 
					<a href="deletecontentt.jsp?id=<%= themeId %>&ref_level=<%= themeContent.getRef_level() %>">[삭제]</a>
					</td>							
				</tr>
				<tr>
					<td><%= themeContent.getWriter() %></td>
					<td><%= themeContent.getContentt() %></td>
				</tr>
				
		<%  } %>
	</table>
	<%} %>
</table>
</c:if>

<script lanaguage="javascript">
	function goReply(){
		document.move.action = "writeForm.jsp";
		document.move.submit();
	}
	function goModify(){
		document.move.action = "updateForm.jsp";
		document.move.submit();
	}
	function goDelete(){
		document.move.action = "deleteForm.jsp";
		document.move.submit();
	}
	function goList(){
		document.move.action = "list.jsp";
		document.move.submit();
	}
	function viewLarge(imgUrl){
		var img = new Image();
		img.src = imgUrl;
		var img_width = img.width;
		var win_width = img.width;
		var img_height = img.height;
		var win = img.height;
		var OpenWindow = window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
		
		OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+imgUrl+"' width='"+win_width+"'>");
	}
</script>

<form name="move" method="post">
	<input type="hidden" name="id" value="${theme.id}">
	<input type="hidden" name="parentId" value="${theme.id}">
	<input type="hidden" name="groupId" value="${theme.groupId}">	
	<input type="hidden" name="page" value="${param.page}">
	
	<c:forEach var="searchCond" items="${paramValues.search_cond}">
		<c:if test="${searchCond == 'title'}">
		<input type="hidden" name="search_cond" value="title">
		</c:if>
		<c:if test="${searchCond == 'name'}">
		<input type="hidden" name="search_cond" value="name">
		</c:if>
	</c:forEach>
	
	<c:if test="${! empty param.search_key}">
	<input type="hidden" name="search_key" value="${param.search_key}">
	</c:if>
</form>