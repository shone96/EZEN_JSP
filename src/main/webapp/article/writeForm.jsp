<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�۾���</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
</head>
<body>

<form action="<c:url value='write.jsp'/>" method="post">
����: <input type="text" name="title" size="20"/><br>
�ۼ���: <input type="text" name="writerName" /><br>
�۾�ȣ: <input type="password" name="password"/><br>
�۳���: <br>
<textarea class="form-contol" name="content"></textarea>
<script type="text/javascript">
CKEDITOR.replace('content', {height: 500});
</script>
<input type="submit" value="����"/>
</form>

</body>
</html>