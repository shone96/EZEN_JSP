<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���ε� ����</title>
</head>
<body>
<%
	//1. isMultipartContent�޼ҵ� ȣ���Ͽ� multlpart/form-data ���� Ȯ��
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	if(isMultipart){
		//2.�޸𸮳� ���Ϸ� ���ε� ���� �����ϴ� FileItem�� Factory ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//3.���ε� ��û�� ó���ϴ� ServletFileUpload ����
		ServletFileUpload upload = new ServletFileUpload(factory) ;
		
		//4.���ε� ��û �Ľ��ؼ� FileItem ��� ����
		List<FileItem> items = upload.parseRequest(request);
		
		Iterator<FileItem> iter = items.iterator();
		while(iter.hasNext()){
			FileItem item = iter.next();
			//5.FileItem�� �� �Է� �׸����� ���ο� �ٶ� �˸��� ó��
			if(item.isFormField()){
				String name = item.getFieldName();
				String value = item.getString("euc-kr");
%>
��û �Ķ����: <%= name %>=<%= value %><br>
<%
			}else{
				String name = item.getFieldName();
				String fileName = item.getName();
				String contentType = item.getContentType();
				boolean isInMemory = item.isInMemory();
				long sizeInBytes = item.getSize();				

%>
���� : <%= name %>, <%= fileName %>, <%= sizeInBytes %> <%= isInMemory ? "�޸�����" : "�ӽ���������" %><br>
<%
			}
		}
	}else {
%>
multipart/form ��û�� �ƴ�.
<%
	}
%>

</body>
</html>