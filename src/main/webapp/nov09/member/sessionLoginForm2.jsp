<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "util.CookieBox" %>     
<html>
<head><title>�α�����</title></head>
<body>
<%
	CookieBox cookieBox = new CookieBox(request);	
%>
<form action="<%= request.getContextPath() %>/nov08/member/sessionLogin2.jsp"
      method="post">
<%
 if(cookieBox.exists("ID")){
%>      
���̵� <input type="text" name="id" size="10" value="<%= cookieBox.getValue("ID") %>">
<% }else{ %>
���̵� <input type="text" name="id" size="10">
<%} %>
��ȣ <input type="password" name="password" size="10">
<%
 if(cookieBox.exists("ID")){
%> 
���̵� ���� <input type="checkbox" name="save" value="true" checked>
<% }else{ %>
���̵� ���� <input type="checkbox" name="save" value="true">
<%} %>
<input type="submit" value="�α���">
</form>

</body>
</html>






