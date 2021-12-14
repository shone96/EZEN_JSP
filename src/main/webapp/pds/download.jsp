<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="pds.service.IncreaseDownloadCountService" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="pds.service.PdsItemNotFoundException" %>
<%@ page import="pds.file.FileDownloadHelper" %>
<%@ page import="pds.model.PdsItem" %>
<%@ page import="pds.service.GetPdsItemService" %>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	try {
		PdsItem item = GetPdsItemService.getInstance().getPdsItem(id);
		
		//응답 헤더 다운로드로 설정
		response.reset();
		
		String fileName = new String(item.getFileName().getBytes("euc-kr"), "iso-8859-1"); //파일 이름 올바르게 출력되도록 변환
		response.setContentType("application/octet-stream"); //다운로드를 위한 콘텐트 타입 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName+"\""); //파일명 설정
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)item.getFileSize());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		FileDownloadHelper.copy(item.getRealPath(), response.getOutputStream()); //FileDownloadHelper을 이용해서 데이터 전송
		
		response.getOutputStream().close();
		
		IncreaseDownloadCountService.getInstance().increaseCount(id);
	}catch(PdsItemNotFoundException ex){
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);//존재하지 않을경우 404 상태 코드 전송
	}
%>