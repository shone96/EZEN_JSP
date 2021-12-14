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
		private Map commandMap = new java.util.HashMap(); //��ɾ�� ��ɾ� ó�� Ŭ������ ����
		
		//��ɾ� ó��Ŭ������ ���εǾ� �ִ� properties ����(Command.properties)�� �о Map ��ü�� commandMap�� ����
		public void init(ServletConfig config) throws ServletException{
			String props = config.getInitParameter("configFile2"); //web.xml���� propertyConfig�� �ش��ϴ� init-param ���� �о��
			Properties pr = new Properties();
			FileInputStream fis = null;
			try {
				String configFilePath = config.getServletContext().getRealPath(props);
				fis = new FileInputStream(configFilePath); //Command.properties������ ������ �о��
				pr.load(fis); //�о�� Command.properties������ ������ Properties��ü�� ����
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
			
			Iterator keyIter = pr.keySet().iterator(); //iterator()�޼ҵ�� �ڷḦ ��, iterator()�޼ҵ�� �÷��� �ڷ����̶�� ������� ��밡��
			while(keyIter.hasNext()) {
				String command = (String) keyIter.next();
				String ClassName = pr.getProperty(command);
				try {
					Class commandClass = Class.forName(ClassName); //�ش� ���ڿ��� Ŭ������ ����
					Object commandInstance = commandClass.newInstance(); //�ش�Ŭ������ ��ü�� ����
					commandMap.put(command, commandInstance);//Map��ü�� commandMap�� ��ü ����
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
		
		//������� ��û�� �м��Ͽ� �ش� �۾��� ó��
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
