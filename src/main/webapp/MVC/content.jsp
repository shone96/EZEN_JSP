<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">

</head>

<body bgcolor="${bodyback_c}">
<center><b>글내용 보기</b><br>
	<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="${bodyback_c}" align="center">
		<form>			
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c}">글번호</td>
				<td align="center" width="125">${article.num}</td>
				<td align="center" width="125" bgcolor="${value_c}">조회수</td>
				<td align="center" width="125">${article.readcount}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c}">작성자</td>
				<td align="center" width="125" align="center">${article.writer}</td>
				<td align="center" width="125" bgcolor="${value_c}">작성일</td>
				<td align="center" width="125" align="center">${article.reg_date}</td>
			</tr>
			<tr height="30">
				<td align="center" width="125" bgcolor="${value_c}">글제목</td>
				<td align="center" width="375" colspan="3">${article.subject}</td>				
			</tr>
			<tr>
				<td align="center" width="125" bgcolor="${value_c}">글내용</td>
				<td align="center" width="375" colspan="3">${article.content}</td>
			</tr>
			<tr height="30">
				<td colspan=4 bgcolor="${value_c}" align="right">
				<input type="button" value="글수정" onclick="document.location.href='/EZEN/MVC/updateForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글삭제" onclick="document.location.href='/EZEN/MVC/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="답글쓰기" onclick="document.location.href='/EZEN/MVC/writeForm.do?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글목록" onclick="document.location.href='/EZEN/MVC/list.do?&pageNum=${pageNum}'">
				</td>
			</tr>
		</table>
		</form>		
</center>
</body>
</html>