<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="util.CookieBox" %>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	if(id.equals(password)){
		session.setAttribute("MEMBERID", id);
		
		if(request.getParameter("chkid") != null){
			response.addCookie(CookieBox.createCookie("ID", id, "/" , -1));
		}else {
			response.addCookie(CookieBox.createCookie("ID", "", "/" , -1));
		}
%>
<html>
<head>
<title>�α��� ����</title>
</head>
<body>

�α��ο� �����߽��ϴ�.

</body>
</html>
<%
	} else {
%>
<script>
alert("�α��ο� �����Ͽ����ϴ�.");
history.go(-1);
</script>
<%
	}
%>