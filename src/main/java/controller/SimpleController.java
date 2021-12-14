package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleController extends HttpServlet{
	//1.HTTP ��û ����, get��İ� post��Ŀ� ���� ��û�� �ϳ��� �޼ҵ��� processRequest()�� ����
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// 2.��û �м�
		// request��ü�� ����ؼ� ����ڰ� ��û�� ����� �ľ�
		String type = request.getParameter("type");
		
		// 3.������� ��û�� ���� �ʿ��� ����� ������ �� ������� ����
		Object resultObject = null;
		if(type == null || type.equals("greeting")) {
			resultObject = "�ȳ��ϼ���";
		}else if(type.equals("date")) {
			resultObject = new java.util.Date();
		}else {
			resultObject = "Invalid Type";
		}
		
		// 4.request ��ü�� session ��ü�� �Ӽ��� ����� ����
		request.setAttribute("result", resultObject);//request��ü�� result�� resultObject�� ����
		
		// 5.RequestDispatcher.forward() �޼ҵ带 ����ؼ� ������� ������ ��� �̵��Ѵ�.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/simpleView.jsp");
		dispatcher.forward(request, response);
	}
	
	
}
