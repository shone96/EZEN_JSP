<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:if test="${param.dest == '1'}">
	<c:redirect url="/use_c_set.jsp" />
</c:if>
<c:if test="${param.dest == '2'}">
	<c:redirect url="use_c_set.jsp" />
</c:if>
<c:if test="${param.dest == '3'}">
	<c:redirect url="/nov30/viewToday.jsp" context="/EZEN" />
</c:if>
<c:if test="${param.dest == '4'}">
	<c:redirect url="https://www.youtube.com/results">
			<c:param name="search_query" value="ryu" />
	</c:redirect>
</c:if>