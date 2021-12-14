package dao;

public class MessageDaoProvider {
	private static MessageDaoProvider instance = new MessageDaoProvider();
	
	public static MessageDaoProvider getinstance() {
		return instance;		
	}
	private MessageDaoProvider() {
		
	}
	
	//private MySQLMessageDao mysqlDao = new MySQLMessageDao();
	private OracleMessageDao oracleDao = new OracleMessageDao();
	//private MSSQLMessageDao mssqlDao = new MSSQLMessageDao();
	private String dbms;
	
	void setDbms(String dbms) {
		this.dbms = dbms;
	}
	
	// MessageDaoProvider.getInstance().getMessageDao()
	public MessageDao getMessageDao() {
		if("oracle".equals(dbms)) {
			return oracleDao;
		}
		return null;
	}
}
