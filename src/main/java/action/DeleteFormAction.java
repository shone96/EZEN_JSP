package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction {
	//content.jsp 글삭제 클릭시 글삭제 폼 처리하는 클래스
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));//해당 글번호
		String pageNum = request.getParameter("pageNum");//해당 페이지 번호		
		
		
		//리턴할 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));		
		
		return "/MVC/deleteForm.jsp";//해당 뷰로 리턴
	}

}
