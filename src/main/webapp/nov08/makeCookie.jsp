<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.net.URLEncoder" %>
<%
	Cookie cookie = new Cookie("name", URLEncoder.encode("ȫ�浿", "euc-kr"));
	response.addCookie(cookie);
%>
<head>
<meta charset="EUC-KR">
<title>��Ű ����</title>
</head>
<body>

<%= cookie.getName() %> ��Ű�� �� = "<%= cookie.getValue() %>"

</body>
</html>