package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;

public class WriteProAction implements CommandAction {//writeForm에 입력된 글 처리하는 클래스
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("euc-kr");
		
		BoardDataBean article = new BoardDataBean(); //데이터처리 자바빈 객체 생성
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		article.setContent(request.getParameter("content"));
		article.setIp(request.getRemoteAddr());
		
		BoardDBBean dbPro = BoardDBBean.getInstance(); //db처리
		dbPro.insertArticle(article);
		
		return "/MVC/writePro.jsp";
	}
}
