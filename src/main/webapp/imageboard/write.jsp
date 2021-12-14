<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="madvirus.gallery.Theme" %>
<%@ page import="madvirus.gallery.ThemeManager" %>
<%@ page import="madvirus.gallery.ThemeManagerException" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="madvirus.util.ImageUtil" %>
<%@ page import="madvirus.fileupload.FileUploadRequestWrapper" %>
<%
	FileUploadRequestWrapper requestWrap = new FileUploadRequestWrapper(request, -1, -1, "D:\\eclipse\\workspace\\EZEN\\src\\main\\webapp\\temp");
	HttpServletRequest tempRequest = request;
	request = requestWrap;
%>
<jsp:useBean id="theme" class="madvirus.gallery.Theme"/>
<jsp:setProperty name="theme" property="*"/>
<%
	FileItem imageFileItem = requestWrap.getFileItem("imageFile");
	String image = "";
	if(imageFileItem.getSize() > 0){
		image = Long.toString(System.currentTimeMillis());
		
		//이미지를 지정한 경로에 저장
		File imageFile = new File("D:\\eclipse\\workspace\\EZEN\\src\\main\\webapp\\image", image);
		
		//중복 이름의 이미지처리
		if(imageFile.exists()){
			for(int i = 0; true; i++){
				imageFile = new File("D:\\eclipse\\workspace\\EZEN\\src\\main\\webapp\\image", image + "_" + i);
				
				if(!imageFile.exists()){
					image = image + "_" + i;
					break;
				}
			}
		}
		imageFileItem.write(imageFile);
		
		//썸네일 이미지 생성
		File destFile = new File("D:\\eclipse\\workspace\\EZEN\\src\\main\\webapp\\image", image + ".small.jpg");
		ImageUtil.resize(imageFile, destFile, 50, ImageUtil.RATIO);
		
	}
	
	theme.setRegister(new Timestamp(System.currentTimeMillis()));
	theme.setImage(image);
	
	ThemeManager manager = ThemeManager.getInstance();
	manager.insert(theme);
%>
<script>
	alert("새로운 이미지를 등록했습니다.");
	location.href="list.jsp";
</script>


