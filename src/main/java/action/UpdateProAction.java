package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;

public class UpdateProAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("euc-kr");		
		
		String pageNum = request.getParameter("pageNum");//해당 페이지 번호		
		
		BoardDataBean article = new BoardDataBean();
		//updateForm에서 보낸 파라미터값들 자바빈 객체에 저장
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		int check = dbPro.updateArticle(article); //위에서 자바빈에 저장한 객체를 updateArticle메소드를 호출해 정상처리시 1 비정상처리시 0 리턴받음
		
		//리턴할 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", check);
		
		return "/MVC/updatePro.jsp";//해당 뷰로 리턴
	}

}
