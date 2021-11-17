<%@ page buffer="none" language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
	버퍼가 없을 경우 <jsp:forward> 액션 태그가 올바르게 실행되지 않을 수도 있다.

--%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>from.jsp 제목</title>
</head>
<body>
이 페이지는 from.jsp가 생성한 것입니다.

<jsp:forward page="/nov05/to/to.jsp"></jsp:forward>

</body>
</html>