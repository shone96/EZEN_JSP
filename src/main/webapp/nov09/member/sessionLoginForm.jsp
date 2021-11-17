<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import="util.CookieBox" %>
<html>
<head><title>로그인폼</title></head>
<body>
<%
	CookieBox cookiebox = new CookieBox(request);
%>
<form action="<%= request.getContextPath() %>/nov09/member/sessionLogin.jsp" method="post">
<%
	if(cookiebox.exists("ID")){		
%>
아이디<input type="text" name="id" size="10" value="<%= cookiebox.getValue("ID") %>">
<%
	} else {
%> 
아이디<input type="text" name="id" size="10">
<%
	}
%>
암호 <input type="password" name="password" size="10">
<%
	if(cookiebox.exists("ID")){		
%>
아이디 저장<input type="checkbox" name="chkid" value="true">
<%
	} else {
%>
아이디 저장<input type="checkbox" name="chkid">
<%
	}
%>
<input type="submit" value="로그인">
</form>

</body>
</html>