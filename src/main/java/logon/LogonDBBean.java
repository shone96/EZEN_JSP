package logon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import loader.JdbcUtil;

public class LogonDBBean {
	//singleton pattern
	private static LogonDBBean instance = new LogonDBBean();
	
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	private LogonDBBean() {
		
	}
	
	private Connection getConnection() throws Exception{
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//inputPro.jsp에서 회원가입시 사용
	public void insertMember(LogonDataBean member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("insert into MEMBERS values (?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getJumin1());
			pstmt.setString(5, member.getJumin2());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getBlog());
			pstmt.setTimestamp(8, member.getReg_date());
			
			pstmt.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException ex){
					
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	
	//loginPro.jsp에서 로그온을 시도할때 호출
	public int userCheck(String id, String passwd) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select passwd from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					x = 1; //인증성공
				}else {
					x = 0; //비밀번호 틀림
				}
			}else {
				x = -1; //해당 아이디 없음
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException ex){
					
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException ex){
					
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException ex){
					
				}
			}
		}
		
		return x;
	}
	
	//confirmId.jsp 에서 아이디 체크할때 사용
	public int confirmId(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select id from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = 1; //해당 아이디 있음
			}else {
				x = -1; //해당 아이디 없음
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException ex){
					
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException ex){
					
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException ex){
					
				}
			}
		}
		
		return x;
	}
	
	//updateMember.jsp에서 수정폼에 가입된 회원의 정보를 보여줄때 사용.
	public LogonDataBean getMember(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select * from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new LogonDataBean();
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setJumin1(rs.getString("jumin1"));
				member.setJumin2(rs.getString("jumin2"));
				member.setEmail(rs.getString("email"));
				member.setBlog(rs.getString("blog"));
				member.setReg_date(rs.getTimestamp("reg_date"));
			}			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException ex){
					
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException ex){
					
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException ex){
					
				}
			}
		}
		
		return member;
	}
	
	public void updateMember(LogonDataBean member) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("update MEMBERS set passwd = ?, name = ?, email = ?, blog = ? where id = ?");			
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());			
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getBlog());
			pstmt.setString(5, member.getId());
			
			
			pstmt.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException ex){
					
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	
	public int deleteMember(String id, String passwd) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select passwd from MEMBERS where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					pstmt = conn.prepareStatement("delete from MEMBERS where id=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					x = 1; //탈퇴 성공
				}else {
					x = 0; //비밀번호 틀림
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException ex){
					
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException ex){
					
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException ex){
					
				}
			}
		}
		
		return x;
	}
	public String findID(String name, String jumin1, String jumin2) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member = new LogonDataBean();
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select id from MEMBERS where name = ? and jumin1 = ? and jumin2 = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, jumin1);
			pstmt.setString(3, jumin2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				member.setId(rs.getString("id"));
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException ex){
					
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException ex){
					
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException ex){
					
				}
			}			
		}

		return member.getId();
	}
	
	public List<LogonDataBean> guestLists() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LogonDataBean> glist = null;
		
		try {
			conn = getConnection();
			
			String sql = "select name, id, jumin1, email, blog from members";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				glist = new ArrayList<LogonDataBean>();
				do {
					LogonDataBean article = new LogonDataBean();
					article.setName(rs.getString("name"));
					article.setId(rs.getString("id"));
					article.setJumin1(rs.getString("jumin1"));
					article.setEmail(rs.getString("email"));
					article.setBlog(rs.getString("blog"));
					
					glist.add(article);
				}while(rs.next());
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return glist;
	}
}
