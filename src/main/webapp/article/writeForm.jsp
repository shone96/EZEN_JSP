<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글쓰기</title>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
</head>
<body>

<form action="<c:url value='write.jsp'/>" method="post">
제목: <input type="text" name="title" size="20"/><br>
작성자: <input type="text" name="writerName" /><br>
글암호: <input type="password" name="password"/><br>
글내용: <br>
<textarea class="form-contol" name="content"></textarea>
<script type="text/javascript">
CKEDITOR.replace('content', {height: 500});
</script>
<input type="submit" value="전송"/>
</form>

</body>
</html>