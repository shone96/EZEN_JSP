<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.net.URLEncoder" %>
<%
	Cookie cookie1 = new Cookie("path1", URLEncoder.encode("경로:/nov08/path1", "euc-kr"));
	cookie1.setPath("/EZEN/nov08/path1");
	response.addCookie(cookie1);
	
	Cookie cookie2 = new Cookie("path2", URLEncoder.encode("경로:", "euc-kr"));	
	response.addCookie(cookie2);
	
	Cookie cookie3 = new Cookie("path3", URLEncoder.encode("경로:/", "euc-kr"));
	cookie3.setPath("/");
	response.addCookie(cookie3);
	
	Cookie cookie4 = new Cookie("path4", URLEncoder.encode("경로:/nov08/path2", "euc-kr"));
	cookie4.setPath("/EZEN/nov08/path2");
	response.addCookie(cookie4);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>쿠키 경로 지정</title>
</head>
<body>
<%= cookie1.getName() %> = <%= cookie1.getValue() %> [<%= cookie1.getPath() %>]<br>
<%= cookie2.getName() %> = <%= cookie2.getValue() %> [<%= cookie2.getPath() %>]<br>
<%= cookie3.getName() %> = <%= cookie3.getValue() %> [<%= cookie3.getPath() %>]<br>
<%= cookie4.getName() %> = <%= cookie4.getValue() %> [<%= cookie4.getPath() %>]

</body>
</html>