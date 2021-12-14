<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="board.BoardDBBean" %>
<%@ page import="board.BoardDataBean" %>
<%@ page import="board.CommentDBBean" %>  
<%@ page import="board.CommentDataBean" %> 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
	function writeSave(){
		if(document.comment.commentt.value == ""){
			alert("작성자를 입력하세요.");
			document.comment.commentt.focus();
			return false;
		}
	}
</script>
</head>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	int pageSize = 10;
	String cPageNum = request.getParameter("cPageNum");
	
	if(cPageNum == null){
		cPageNum = "1";
	}
	
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage * 10) - 9; 
	int endRow = currentPage * pageSize; 	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	try{
		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardDataBean article = dbPro.getArticle(num);
		CommentDBBean cdb = CommentDBBean.getIntance();
		ArrayList comments = cdb.getComments(article.getNum(),startRow, endRow);
		
		int count = cdb.getCommentCount(article.getNum());
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
%>

<body bgcolor="<%= bodyback_c %>">
<center><b>글내용 보기</b><br>
	<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="<%= bodyback_c %>" align="center">
		<form>			
			<tr height="30">
				<td align="center" width="125" bgcolor="<%= value_c %>">글번호</td>
				<td align="center" width="125"><%= article.getNum() %></td>
				<td align="center" width="125" bgcolor="<%= value_c %>">조회수</td>
				<td align="center" width="125"><%= article.getReadcount() %></td>
			</tr>
			<tr height="30">
				<td align="center" width="125" bgcolor="<%= value_c %>">작성자</td>
				<td align="center" width="125"><%= article.getWriter() %></td>
				<td align="center" width="125" bgcolor="<%= value_c %>">작성일</td>
				<td align="center" width="125"><%= sdf.format(article.getReg_date()) %></td>
			</tr>
			<tr height="30">
				<td align="center" width="125" bgcolor="<%= value_c %>">글제목</td>
				<td align="center" width="375" colspan="3"><%= article.getSubject() %></td>				
			</tr>
			<tr>
				<td align="center" width="125" bgcolor="<%= value_c %>">글내용</td>
				<td align="center" width="375" colspan="3"><%= article.getContent() %></td>
			</tr>
			<tr height="30">
				<% if(session.getAttribute("memId") != null){ %>
				<td colspan=4 bgcolor="<%= value_c %>" align="right">
				<input type="button" value="글수정" onclick="document.location.href='updateForm.jsp?num=<%= article.getNum() %>&pageNum=<%= pageNum %>'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글삭제" onclick="document.location.href='deleteForm.jsp?num=<%= article.getNum() %>&pageNum=<%= pageNum %>'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="답글쓰기" onclick="document.location.href='writeForm.jsp?num=<%= num %>&ref=<%= ref %>&re_step=<%= re_step %>&re_level=<%= re_level %>'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글목록" onclick="document.location.href='list.jsp?&pageNum=<%= pageNum %>'">
				</td>
				<% }else { %>
				<td colspan=4 bgcolor="<%= value_c %>" align="right">
				<input type="button" value="답글쓰기" onclick="document.location.href='writeForm.jsp?num=<%= num %>&ref=<%= ref %>&re_step=<%= re_step %>&re_level=<%= re_level %>'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글목록" onclick="document.location.href='list.jsp?&pageNum=<%= pageNum %>'">
				</td>
				<% } %>
			</tr>
		</form>
		<form method="post" action="contentPro.jsp" name="comment" onsubmit="return writeSave()">
		<tr bgcolor="<%= value_c %>" align="center">
			<td>코멘트 작성</td>
			<td colspan="2">
				<textarea name="commentt" rows="6" cols="40"></textarea>
				<input type="hidden" name="content_num" value="<%= article.getNum() %>">
				<input type="hidden" name="p_num" value="<%= pageNum %>">
				<input type="hidden" name="comment_num" value="<%= count+1 %>">
			</td>
			<td align="center">
				작성자<br>
				<input type="text" name="commenter" size="10"><br> 
				비밀번호<br>
				<input type="password" name="passwd" size="10"><br>
				<input type="submit" value="코멘트달기"> 
			</td>
		</tr>			
		</form>
	</table>
	<%if(count > 0) {%>
	<p>
	<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="<%= bodyback_c %>" align="center">
		<tr>
			<td>코멘트 수 : <%= comments.size() %>
		</tr>
		<% for(int i = 0; i < comments.size(); i++) {
			CommentDataBean dbc = (CommentDataBean) comments.get(i);
		%>
		<tr>
			<td align="left" size="250" bgcolor="<%= value_c %>">&nbsp;
			<b><%= dbc.getCommenter() %>&nbsp;님</b>(<%= sdf.format(dbc.getReg_date()) %>)
			</td>
			<td align="right" size="250" bgcolor="<%= value_c %>"> 접속IP : <%= dbc.getIp() %>&nbsp;
			<% if(session.getAttribute("memId") != null) {%>
			<a href="contentWrite.jsp?ctn=<%= dbc.getContent_num() %>&cmn=<%= dbc.getComment_num() %>&p_num=<%= pageNum %>">[댓글]</a>&nbsp;
			<a href="delCommentForm.jsp?ctn=<%= dbc.getContent_num() %>&cmn=<%= dbc.getComment_num() %>&p_num=<%= pageNum %>">[삭제]</a>&nbsp;
			<% } %>
			</td>			
		</tr>
		<tr>
			<td colspan="2"><%= dbc.getCommentt() %></td>
		<%} %>
		</tr>
	</table>
	<%} %>
	<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="<%= bodyback_c %>" align="center">
	<tr>
	<center>
<%
	if(count > 0){
		//전체 페이지의 수를 연산
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		int startPage = (int)(currentPage / 5) * 5 + 1;
		int pageBlock = 5;
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		if(startPage > 5) {				
	%>
			<a href="content.jsp?num=<%= num %>&pageNum=<%= pageNum %>&CpageNum=<%= startPage - 5 %>">[이전]</a>
	  <%}%>
	<%		
		for(int i = startPage; i <= endPage; i++) {		
	 %>
			<a href="content.jsp?num=<%= num %>&pageNum=<%= pageNum %>&cPageNum=<%= i %>">[<%= i %>]</a>
	  <%}
		
		if(endPage < pageCount) {%>
			<a href="content.jsp?num=<%= num %>&pageNum=<%= pageNum %>&CpageNum=<%= startPage + 5 %>">[다음]</a>
	  <%}		
	}	
%>
	</center>
	</tr>
	</table>
	<%} catch(Exception e) {}%>
</center>
</body>
</html>