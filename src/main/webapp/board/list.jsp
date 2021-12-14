<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="board.BoardDataBean" %>
<%@ page import="board.BoardDBBean" %>
<%@ page import="board.CommentDBBean" %>
<%@ page import="board.CommentDataBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="logon.*" %>
<%@ include file="../board/color.jsp" %>
<%!
	int pageSize = 10;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<%	
	String pageNum = request.getParameter("pageNum");
	String search = request.getParameter("search");
	
	int searchn = 0;
	
	if(pageNum == null){
		pageNum = "1";
	}
	
	if(search == null){
		search = "";
	}else{
		searchn = Integer.parseInt(request.getParameter("searchn"));
	}
	
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage * 10) - 9; 
	int endRow = currentPage * pageSize; 
	int count = 0;
	int number = 0;
	
	List articleList = null;
	BoardDBBean dbPro = BoardDBBean.getInstance();
	
	if(search.equals("") || search == null){
		count = dbPro.getArticleCount(); //�Խñ� ����		
	}else{
		count = dbPro.getArticleCount(searchn, search); //�˻������� �Խñ� ����
	}
	
	CommentDBBean cdb = CommentDBBean.getIntance();
	
	if(count > 0){
		if(search.equals("") || search == null){
			articleList = dbPro.getArticles(startRow, endRow);			
		}else{
			articleList = dbPro.getArticles(startRow, endRow, searchn, search);
		}
	}
	
	number = count - (currentPage - 1) * pageSize;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ���</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%= bodyback_c %>">
<center><b>�۸��(��ü ��:<%= count %>)</b>
<table width="700">
	<tr>
		<% if(session.getAttribute("memId") != null){ %>
		<td align="left" bgcolor="<%= value_c %>">
		<%= session.getAttribute("memId") %>�� �ݰ����ϴ�.
		<input type="button" value="�α׾ƿ�" onclick="document.location.href='../logon/logout.jsp'">
		</td>
		<td align="right" bgcolor="<%= value_c %>">
		<a href="writeForm.jsp">�۾���</a>
		</td>
		<% }else { %>
		<td align="right" bgcolor="<%= value_c %>">
		<a href="../logon/main.jsp">�α���</a>&nbsp;&nbsp;
		<a href="../logon/inputForm.jsp">ȸ������</a>
		</td>
		<% } %>
	</tr>
</table>
<%
	if(count == 0) {
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
		�Խ��ǿ� ����� ���� �����ϴ�.
		</td>
	</tr>
</table>
<%  } else { %>
<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
	<tr height="30" bgcolor="<%= value_c %>">
		<td align="center" width="50">��ȣ</td>
		<td align="center" width="250">����</td>
		<td align="center" width="100">�ۼ���</td>
		<td align="center" width="150">�ۼ���</td>
		<td align="center" width="50">��ȸ</td>
		<td align="center" width="100">IP</td>
	</tr>
<%
	for(int i = 0; i < articleList.size(); i++){
		BoardDataBean article = (BoardDataBean) articleList.get(i);	
		int com_count = cdb.getCommentCount(article.getNum());
%>
	<tr height="30">
		<td align="center" width="50"> <%= number-- %></td>
		<td align="left">
<%
	int wid = 0;
	if(article.getRe_level() > 0) { //�亯���̸�
		wid = 5 * (article.getRe_level());
		
%>	
	<a href="content.jsp?num=<%= article.getNum() %>&pageNum=<%= currentPage %>"></a>
	<img src="images/level.gif" width="<%= wid %>" height="16">
	<img src="images/re.gif">
<%  } else { %>
	<img src="images/level.gif" width="<%= wid %>" height="16">
<%  }%>	
	
	<%if(com_count > 0) {%>
		<a href="content.jsp?num=<%= article.getNum() %>&pageNum=<%= currentPage %>"><%= article.getSubject() %>[<%= com_count %>]</a>
	<%}else { %>
		<a href="content.jsp?num=<%= article.getNum() %>&pageNum=<%= currentPage %>"><%= article.getSubject() %></a>
	<%} %>
	 
  
	<% if(article.getReadcount() >= 20) {%>
	<img src="images/hot.gif" border="0" height="16">
	<% } %>
	</td>
	<td align="center" width="100">
		<a href="mailto:<%= article.getEmail() %>"><%= article.getWriter() %></a>
	</td>
	<td align="center" width="150"><%= sdf.format(article.getReg_date()) %></td>
	<td align="center" width="50"><%= article.getReadcount() %></td>
	<td align="center" width="100"><%= article.getIp() %></td>
	</tr>
	<%} %>	
</table>
<% } %>
<p>
<%
	if(count > 0){
		//��ü �������� ���� ����
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		int startPage = (int)(currentPage / 5) * 5 + 1;
		int pageBlock = 5;
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		if(startPage > 5) {
			if(search.equals("") || search == null){				
%>
			<a href="list.jsp?pageNum=<%= startPage - 5 %>">[����]</a>
<%			}else{ %>
			<a href="list.jsp?pageNum=<%= startPage - 5 %>&search=<%= search %>&searchn=<%= searchn %>">[����]</a>
		  <%}%>
<%
		}
		
		for(int i = startPage; i <= endPage; i++) {
			if(search.equals("") || search == null) {			
%>
			<a href="list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%			}else { %>
			<a href="list.jsp?pageNum=<%= i %>&search=<%= search %>&searchn=<%= searchn %>">[<%= i %>]</a>
			<%}%>
<%
		}
		
		if(endPage < pageCount) {
			if(search.equals("") || search == null) {			
%>
			<a href="list.jsp?pageNum=<%= startPage + 5 %>">[����]</a>
<%			}else{ %>
			<a href="list.jsp?pageNum=<%= startPage + 5 %>&search=<%= search %>&searchn=<%= searchn %>">[����]</a>
		  <%}%>
<%
		}
	}	
%>
</p>
<form>
<%
	if(session.getAttribute("memId") != null){
%>
<select name="searchn">
	<option value="0">�ۼ���</option>
	<option value="1">����</option>
	<option value="2">����</option>
</select>
<input type="text" name="search" size="15" maxlength="50" />
<input type="submit" value="�˻�" />
</form>
<%  } %>
<br>
</center>
</body>
</html>