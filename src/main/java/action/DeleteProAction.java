package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;

public class DeleteProAction implements CommandAction {//글 삭제기능 클래스
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("euc-kr");		
		
		int num = Integer.parseInt(request.getParameter("num")); //해당 글 번호
		String pageNum = request.getParameter("pageNum");//해당 페이지 번호
		String passwd = request.getParameter("passwd");		
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		int check = dbPro.deleteArticle(num, passwd); //deleteArticle 메소드에 (글번호, 입력받은 비밀번호)를 넣어서 정상작동시 1 비정상작동시 0리턴
		
		//리턴할 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", check);
		
		return "/MVC/deletePro.jsp";//해당 뷰로 리턴
	}

}
