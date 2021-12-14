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

public class ControllerUsingFile extends HttpServlet{ //HttpServlet�� ��ӹ޾� ��Ʈ�ѷ� �����̴�
	
	//<Ŀ��Ʈ, �ڵ鷯�ν��Ͻ�> ���� ���� ����
	private Map commandHandlerMap = new java.util.HashMap();
	
	public void init(ServletConfig config) throws ServletException{
		String configFile = config.getInitParameter("configFile"); //configFile �ʱ�ȭ �Ķ���ͷκ��� ���� ������ �����ϰ� �ִ� ���� ������ ��θ� ���Ѵ�.
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			//��������(configFile)�κ��� ���� ������ �о�� Properties ��ü�� ����
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
			//29���ο��� Properties ��ü�� ����� ���� ������ �̿��Ͽ� <��ɾ�, �ڵ鷯�ν��Ͻ�>�� ���� ������ commandHandlerMap(HashMap)�� ����
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
		CommandHandler handler = (CommandHandler) commandHandlerMap.get(command); //��û�� ó���� �� ���� ��ɾ� �ڵ鷯 �ν��Ͻ��� commandHandlerMap���κ��� ���Ѵ�		 
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = null;
		try {
			//�ڵ鷯 �ν��Ͻ��� process() �޼ҵ带 ȣ���ؼ� ��û�� ó���ϰ�, ����� ������ �� �������� URI�� ���� ������ ���޹޴´�
			viewPage = handler.process(request, response);
		}catch(Throwable e) {
			throw new ServletException(e);
		}		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
	

}
