<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>파일업로드 폼</title>
</head>
<body>
<form action="fileupload_data.jsp" method="post" enctype="multipart/form-data">
text1 : <input type="text" name="text1"/><br>
file1 : <input type="file" name="file1"/><br>
file2 : <input type="file" name="file2"/><br>
<input type="submit" value="전송"/>
</form>

</body>
</html>