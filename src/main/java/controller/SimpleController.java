package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleController extends HttpServlet{
	//1.HTTP 요청 받음, get방식과 post방식에 대한 요청을 하나의 메소드인 processRequest()로 전달
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// 2.요청 분석
		// request객체를 사용해서 사용자가 요청한 기능을 파악
		String type = request.getParameter("type");
		
		// 3.사용자의 요청에 따라 필요한 기능을 수행한 뒤 결과값을 생성
		Object resultObject = null;
		if(type == null || type.equals("greeting")) {
			resultObject = "안녕하세요";
		}else if(type.equals("date")) {
			resultObject = new java.util.Date();
		}else {
			resultObject = "Invalid Type";
		}
		
		// 4.request 객체나 session 객체의 속성에 결과값 저장
		request.setAttribute("result", resultObject);//request객체에 result로 resultObject값 저장
		
		// 5.RequestDispatcher.forward() 메소드를 사용해서 결과값을 보여줄 뷰로 이동한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/simpleView.jsp");
		dispatcher.forward(request, response);
	}
	
	
}
