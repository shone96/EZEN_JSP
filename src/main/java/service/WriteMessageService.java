package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import connection.ConnectionProvider;
import dao.MessageDao;
import dao.MessageDaoProvider;
import loader.JdbcUtil;
import model.Message;
import model.MessageListView;

public class WriteMessageService {
	private static WriteMessageService instance = new WriteMessageService();
	
	public static WriteMessageService getInstance() {
		return instance;
	}
	
	private WriteMessageService() {
		
	}	
	
	//write.jsp���� ���� �޽��� ���涧 ���
	public void write(Message message) throws ServiceException{
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			MessageDao messageDao = MessageDaoProvider.getinstance().getMessageDao();
			messageDao.insert(conn, message);			
			
		}catch(SQLException e) {
			throw new ServiceException("�޽��� ��� ����: " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
