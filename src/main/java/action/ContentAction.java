package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;

public class ContentAction implements CommandAction {
	//�۳��� �󼼺��� �� ó���ϴ� Ŭ����
	//list.jsp -> /EZEN/MVC/content.do uri -> web.xml���� ControllerUsingURI2.java(��Ʈ�ѷ�) <- commandHandlerURI.properties
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));//�ش� �۹�ȣ
		String pageNum = request.getParameter("pageNum");//�ش� ������ ��ȣ
		
		BoardDBBean dbPro = BoardDBBean.getInstance();//dbó��
		BoardDataBean article = dbPro.getArticle(num);//getArticle()�޼ҵ带 �̿��� �ش� �۹�ȣ�� ���� �����͸� ������
		
		//������ �信�� ����� �Ӽ�
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/MVC/content.jsp";//�ش� ��� ����
	}
	

}
