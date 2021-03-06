<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>numberFormat 태그 사용</title>
</head>
<body>

<c:set var="price" value="10000"/>
<fmt:formatNumber value="${price}" type="number" var="numberType"></fmt:formatNumber>
<br>
통화: <fmt:formatNumber value="${price}" type="currency" currencySymbol="원"></fmt:formatNumber>
<br>
퍼센트: <fmt:formatNumber value="${price}" type="percent" groupingUsed="false"></fmt:formatNumber>
<br>
숫자: ${numberType}
<br>
패턴: <fmt:formatNumber value="${price}" pattern="00000000.00"></fmt:formatNumber>

</body>
</html>