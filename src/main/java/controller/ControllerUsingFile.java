package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import command.NullHandler;

public class ControllerUsingFile extends HttpServlet{ //HttpServlet를 상속받아 컨트롤러 서블릿이다
	
	//<커맨트, 핸들러인스턴스> 매핑 정보 저장
	private Map commandHandlerMap = new java.util.HashMap();
	
	public void init(ServletConfig config) throws ServletException{
		String configFile = config.getInitParameter("configFile"); //configFile 초기화 파라미터로부터 매핑 정보를 저장하고 있는 설정 파일의 경로를 구한다.
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			//설정파일(configFile)로부터 매핑 정보를 읽어와 Properties 객체에 저장
			String configFilePath = config.getServletContext().getRealPath(configFile);
			fis = new FileInputStream(configFilePath);
			prop.load(fis);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if(fis != null) {
				try {
					fis.close();
				}catch (IOException ex) {
					
				}
			}
		}		
		
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			//29라인에서 Properties 객체에 저장된 매핑 정보를 이용하여 <명령어, 핸들러인스턴스>의 매핑 정보를 commandHandlerMap(HashMap)에 저장
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			try {
				Class handlerClass = Class.forName(handlerClassName);
				Object handlerInstance = handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			}catch(ClassNotFoundException e) {
				throw new ServletException(e);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String command = request.getParameter("cmd");
		CommandHandler handler = (CommandHandler) commandHandlerMap.get(command); //요청을 처리할 때 사용될 명령어 핸들러 인스턴스를 commandHandlerMap으로부터 구한다		 
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = null;
		try {
			//핸들러 인스턴스의 process() 메소드를 호출해서 요청을 처리하고, 결과로 보여줄 뷰 페이지의 URI를 리턴 값으로 전달받는다
			viewPage = handler.process(request, response);
		}catch(Throwable e) {
			throw new ServletException(e);
		}		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
	

}
