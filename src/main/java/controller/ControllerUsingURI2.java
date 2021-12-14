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

import action.CommandAction;
import action.NullAction;

public class ControllerUsingURI2 extends HttpServlet {
		private Map commandMap = new java.util.HashMap(); //명령어와 명령어 처리 클래스를 저장
		
		//명령어 처리클래스가 매핑되어 있는 properties 파일(Command.properties)을 읽어서 Map 객체인 commandMap에 저장
		public void init(ServletConfig config) throws ServletException{
			String props = config.getInitParameter("configFile2"); //web.xml에서 propertyConfig에 해당하는 init-param 값을 읽어옴
			Properties pr = new Properties();
			FileInputStream fis = null;
			try {
				String configFilePath = config.getServletContext().getRealPath(props);
				fis = new FileInputStream(configFilePath); //Command.properties파일의 내용을 읽어옴
				pr.load(fis); //읽어온 Command.properties파일의 정보를 Properties객체에 저장
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
			
			Iterator keyIter = pr.keySet().iterator(); //iterator()메소드로 자료를 얻어냄, iterator()메소드는 컬렉션 자료형이라면 관계없이 사용가능
			while(keyIter.hasNext()) {
				String command = (String) keyIter.next();
				String ClassName = pr.getProperty(command);
				try {
					Class commandClass = Class.forName(ClassName); //해당 문자열을 클래스로 만듬
					Object commandInstance = commandClass.newInstance(); //해당클래스의 객체를 생성
					commandMap.put(command, commandInstance);//Map객체인 commandMap에 객체 저장
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
			requestPro(request, response);
		}
		
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			requestPro(request, response);
		}
		
		//사용자의 요청을 분석하여 해당 작업을 처리
		private void requestPro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
			String view = null;
			CommandAction com = null;
			
			try {
				String command = request.getRequestURI();
				if(command.indexOf(request.getContextPath()) == 0) {
					command = command.substring(request.getContextPath().length());
				}
				com = (CommandAction) commandMap.get(command);
				
				if(com == null) {
					com = new NullAction();
				}
				view = com.requestPro(request, response) ;
			}catch(Throwable e) {
				throw new ServletException(e);
			}		
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
}
