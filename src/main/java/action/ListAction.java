package action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;

public class ListAction implements CommandAction {//글목록 처리
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pageNum = request.getParameter("pageNum"); //param값에서 페이지 번호 가져옴
		
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;//한페이지의 시작글 번호
		int endRow = currentPage * pageSize;//한페이지의 마지막 글번호
		int count = 0;
		int number = 0;
		
		List articleList = null;
		BoardDBBean dbPro = BoardDBBean.getInstance();
		count = dbPro.getArticleCount();//전체 게시글 수
		
		if(count > 0) {
			articleList = dbPro.getArticles(startRow, endRow);// 현재 페이지에 해당하는 글 목록
		}else {
			articleList = Collections.EMPTY_LIST;
		}
		
		number = count - (currentPage - 1) * pageSize;//글목록에 표시할 글번호
		//리턴 시킬 뷰에서 사용할 속성 설정
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		//list.jsp에 현재페이지, 시작페이지, 끝페이지, 게시글 전체 갯수, 페이징처리 숫자, number, 현재 페이지에 해당하는 글 목록을 저장한 list객체를 사용할 속성으로 설정
		return "/MVC/list.jsp";//해당뷰
		
	}

}
