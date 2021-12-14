package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import loader.JdbcUtil;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	
	public static BoardDBBean getInstance() {
		return instance;
	}
	
	private BoardDBBean() {
		
	}
	
	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//writePro.jsp writeForm.jsp에서 글입력에대한 처리
	public void insertArticle(BoardDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		int number = 0;
		String sql = "";
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				number = rs.getInt(1) + 1;
			}else {
				number = 1;
			}
			
			if(num != 0) { //답변글 처리, article.getNum(게시물번호)가 0이아니면 글작성한 게시물
				sql = "update board set re_step = re_step+1 where ref = ? and re_step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step = re_step + 1;
				re_level = re_level + 1;
			}else { 
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			sql = "insert into board(num, writer, email, subject, passwd, reg_date, ";
			sql += "ref, re_step, re_level, content, ip) values(board_num.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	//list.jsp : 페이징을 위해서 전체 테이블에 입력된 행의수가 필요
	public int getArticleCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return x;
	}
	
	//list.jsp에서 검색결과 보여주기위한 getArticleCount 메소드 오버로딩
	public int getArticleCount(int n, String searchKeyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String[] column_name = {"writer", "subject", "content"};
		
		int x = 0;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select count(*) from board where " + column_name[n] + " like '%" + searchKeyword + "%'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return x;
	}
	
	//list.jsp의 페이징 처리
	public List<BoardDataBean> getArticles(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDataBean> articleList = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
			"select num, writer, email, subject, passwd, reg_date, re_step, ref, re_level, content, ip, readcount, r " +
			"from (select num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip, readcount, rownum r " +
			"from (select num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip, readcount " +
			"from board order by ref desc, re_step asc) order by ref desc, re_step asc) where r >= ? and r <= ? "
			);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleList = new ArrayList<BoardDataBean>(end);
				do {
					BoardDataBean article = new BoardDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);//rs에서 데이터 가져온값을 article 자바빈에 넣은 데이터를 articleList 리스트에 추가					
				}while(rs.next());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return articleList;
	}
	//list.jsp의 검색결과 페이징 처리를 위한 getArticles 메소드 오버로딩
	public List getArticles(int start, int end, int n, String searchKeyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		
		String[] column_name = {"writer", "subject", "content"};
		
		try {
			conn = getConnection();
			
			String sql = "select num, writer, email, subject, passwd, reg_date, re_step, ref, re_level, content, ip, readcount, r " +
					"from (select num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip, readcount, rownum r " +
					"from (select num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip, readcount " +
					"from board order by ref desc, re_step asc) where " + column_name[n] + " like '%" +searchKeyword +"%' order by ref desc, re_step asc) where r >= ? and r <= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList<BoardDataBean>(end);
				do {
					BoardDataBean article = new BoardDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);//rs에서 데이터 가져온값을 article 자바빈에 넣은 데이터를 articleList 리스트에 추가					
				}while(rs.next());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return articleList;
	}
	
	//content.jsp : DB로부터 한줄의 데이터를 가져온다.
	public BoardDataBean getArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("update board set readcount = readcount + 1 where num = ?"); //조회수
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
					
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return article;
	}
	
	//updateForm.jsp 수정폼에 한줄의 데이터를 가져올때.
	public BoardDataBean updateGetArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return article;
	}
	
	//updatePro.jsp 수정폼에 수정한 데이터를 수정하는 메소드 
	public int updateArticle(BoardDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		String sql="";
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select passwd from board where num = ?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//입력받은 비밀번호와 rs에서 가져온 비밀번호가 일치하면 수정후 1리턴 아니면 0리턴
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(article.getPasswd())) {
					sql = "update board set writer = ?, email = ?, subject = ?, passwd = ?";
					sql += ", content = ? where num = ?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getPasswd());
					pstmt.setString(5, article.getContent());
					pstmt.setInt(6, article.getNum());
					pstmt.executeUpdate();
					x = 1;
				}else {
					x = 0;
				}
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return x;
	}
	
	//deletePro.jsp : updateForm.jsp 에서 글삭제 버튼 클릭시 실행되는 메소드
	//게시글 하나 삭제하는 메소드
	/*public int deleteArticle(int num, String passwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select passwd from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//입력받은 비밀번호와 rs에서 가져온 비밀번호가 일치하면 수정후 1리턴 아니면 0리턴
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					pstmt = conn.prepareStatement("delete from board where num = ?");					
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					x = 1; //삭제 성공
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
	}*/
	public int deleteArticle(int num, String passwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd="";
		int Ref;
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select passwd, ref from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();			
			
			if(rs.next()) {//입력받은 비밀번호와 rs에서 가져온 비밀번호가 일치하면 수정후 1리턴 아니면 0리턴
				dbpasswd = rs.getString("passwd");				
				Ref = rs.getInt("ref");
				if(dbpasswd.equals(passwd)) {					
					String sql = "select count(*) from board where ref = " + Ref + "";
					pstmt =	conn.prepareStatement(sql);					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						if(rs.getInt(1) > 1) {
							pstmt = conn.prepareStatement("update board set subject = '[삭제됨]' , writer = '[삭제]' where ref = ?");					
							pstmt.setInt(1, Ref);
							pstmt.executeUpdate();							
						}else {
							pstmt = conn.prepareStatement("delete from board where num = ?");					
							pstmt.setInt(1, num);
							pstmt.executeUpdate();
						}
					}
					x = 1; //삭제 성공					
					
				}else {
					x = 0; //비밀번호 틀림
				}
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return x;
	}
	
	public int findRelevel(int ref) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = -1;
		
		try {
			conn = getConnection();
			 pstmt = conn.prepareStatement("select * from board where ref = ? and re_level = 0");
			 pstmt.setInt(1, ref);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 x = 1;
			 }else {
				 x = 0;
			 }
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return x;
	}

}
