package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;

public class ContentAction implements CommandAction {
	//글내용 상세보기 폼 처리하는 클래스
	//list.jsp -> /EZEN/MVC/content.do uri -> web.xml에서 ControllerUsingURI2.java(컨트롤러) <- commandHandlerURI.properties
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));//해당 글번호
		String pageNum = request.getParameter("pageNum");//해당 페이지 번호
		
		BoardDBBean dbPro = BoardDBBean.getInstance();//db처리
		BoardDataBean article = dbPro.getArticle(num);//getArticle()메소드를 이용해 해당 글번호에 대한 데이터를 가져옴
		
		//리턴할 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/MVC/content.jsp";//해당 뷰로 리턴
	}
	

}
