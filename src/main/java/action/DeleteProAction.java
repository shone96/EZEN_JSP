package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;

public class DeleteProAction implements CommandAction {//�� ������� Ŭ����
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("euc-kr");		
		
		int num = Integer.parseInt(request.getParameter("num")); //�ش� �� ��ȣ
		String pageNum = request.getParameter("pageNum");//�ش� ������ ��ȣ
		String passwd = request.getParameter("passwd");		
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		int check = dbPro.deleteArticle(num, passwd); //deleteArticle �޼ҵ忡 (�۹�ȣ, �Է¹��� ��й�ȣ)�� �־ �����۵��� 1 �������۵��� 0����
		
		//������ �信�� ����� �Ӽ�
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", check);
		
		return "/MVC/deletePro.jsp";//�ش� ��� ����
	}

}
