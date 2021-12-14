package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;

public class UpdateProAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("euc-kr");		
		
		String pageNum = request.getParameter("pageNum");//�ش� ������ ��ȣ		
		
		BoardDataBean article = new BoardDataBean();
		//updateForm���� ���� �Ķ���Ͱ��� �ڹٺ� ��ü�� ����
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		int check = dbPro.updateArticle(article); //������ �ڹٺ� ������ ��ü�� updateArticle�޼ҵ带 ȣ���� ����ó���� 1 ������ó���� 0 ���Ϲ���
		
		//������ �信�� ����� �Ӽ�
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", check);
		
		return "/MVC/updatePro.jsp";//�ش� ��� ����
	}

}
