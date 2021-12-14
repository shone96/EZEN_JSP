<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="color.jsp" %>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>코멘트 댓글 작성</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script>
	function checkcomment(){
		if(document.contentWriteForm.commentt.value == ""){
			alert("코멘트를 작성해주세요.");
			document.contentWriteForm.commentt.focus();
			return false;
		}
		if(document.contentWriteForm.commenter.value == ""){
			alert("작성자를 작성해주세요.");
			document.contentWriteForm.commenter.focus();
			return false;
		}
		if(document.contentWriteForm.passwd.value == ""){
			alert("비밀번호를 작성해주세요.");
			document.contentWriteForm.passwd.focus();
			return false;
		}
	}
</script>
</head>
<body bgcolor="<%= bodyback_c %>">
<form name="contentWriteForm" method="post" action="contentWritePro.jsp" onclick="return checkcomment()">
	<table border="1" align="center">
		<tr bgcolor="<%= value_c %>" align="center">
			<td>코멘트 작성</td>
			<td colspan="2">
				<textarea name="commentt" rows="6" cols="40"></textarea>
			</td>
			<td align="center">
				작성자<br>
				<input type="text" name="commenter" size="10"><%=  %><br> 
				비밀번호<br>
				<input type="password" name="passwd" size="10"><br>
				<input type="submit" value="코멘트달기"> 
			</td>
		</tr>
	</table>
</form>

</body>
</html>