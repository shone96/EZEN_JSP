package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;

public class UpdateFormAction implements CommandAction {//글수정하는 폼 클래스
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));//해당 글번호
		String pageNum = request.getParameter("pageNum");//해당 페이지 번호
		
		BoardDBBean dbPro = BoardDBBean.getInstance();//db처리
		BoardDataBean article = dbPro.updateGetArticle(num);//updateGetArticle()메소드를 이용해 해당 글번호 데이터를 수정
		
		//리턴할 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/MVC/updateForm.jsp";//해당 뷰로 리턴
	}
}
