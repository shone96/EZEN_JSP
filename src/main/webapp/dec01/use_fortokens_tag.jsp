<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>fortokens 태그</title>
</head>
<body>
콤마와 점을 구분자로 사용 : <br>
<c:forTokens var="token" items="빨강색,노랑색,주황색.파랑색.검은색" delims=",.">
	${token}
</c:forTokens>

</body>
</html>