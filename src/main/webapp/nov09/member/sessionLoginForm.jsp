<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import="util.CookieBox" %>
<html>
<head><title>�α�����</title></head>
<body>
<%
	CookieBox cookiebox = new CookieBox(request);
%>
<form action="<%= request.getContextPath() %>/nov09/member/sessionLogin.jsp" method="post">
<%
	if(cookiebox.exists("ID")){		
%>
���̵�<input type="text" name="id" size="10" value="<%= cookiebox.getValue("ID") %>">
<%
	} else {
%> 
���̵�<input type="text" name="id" size="10">
<%
	}
%>
��ȣ <input type="password" name="password" size="10">
<%
	if(cookiebox.exists("ID")){		
%>
���̵� ����<input type="checkbox" name="chkid" value="true">
<%
	} else {
%>
���̵� ����<input type="checkbox" name="chkid">
<%
	}
%>
<input type="submit" value="�α���">
</form>

</body>
</html>