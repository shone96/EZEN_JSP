package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;

public class UpdateFormAction implements CommandAction {//�ۼ����ϴ� �� Ŭ����
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));//�ش� �۹�ȣ
		String pageNum = request.getParameter("pageNum");//�ش� ������ ��ȣ
		
		BoardDBBean dbPro = BoardDBBean.getInstance();//dbó��
		BoardDataBean article = dbPro.updateGetArticle(num);//updateGetArticle()�޼ҵ带 �̿��� �ش� �۹�ȣ �����͸� ����
		
		//������ �信�� ����� �Ӽ�
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/MVC/updateForm.jsp";//�ش� ��� ����
	}
}
