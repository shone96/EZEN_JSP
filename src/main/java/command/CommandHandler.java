package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	//��ɾ� �ڵ鷯 Ŭ�������� process()�޼ҵ忡�� �˸��� ���� �ڵ带 ������ �� ����� ������ JSP�� URI�� ����
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
