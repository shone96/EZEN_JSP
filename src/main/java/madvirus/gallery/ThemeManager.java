package madvirus.gallery;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import loader.JdbcUtil;
import madvirus.sequence.Sequencer;

public class ThemeManager {
	private static ThemeManager instance = new ThemeManager();
	
	public static ThemeManager getInstance() {
		return instance;
	}
	
	private ThemeManager() {
		
	}
	
	private Connection getConnection() throws Exception {
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:/pool");
	}
	
	
	//새 글 삽입시
	public void insert(Theme theme) throws Exception {
		Connection conn = null;
		//새로운 글의 그룹 번호를 구할 때 사용
		Statement stmtGroup = null;
		ResultSet rsGroup = null;
		
		//특정 글의 답글에 대한 출력 순서를 구할 때 사용
		PreparedStatement pstmtOrder = null;
		ResultSet rsOrder = null;
		PreparedStatement pstmtOrderUpdate = null;
		
		//글 삽입할때
		PreparedStatement pstmtInsertMessage = null;
		PreparedStatement pstmtInsertContent = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			if(theme.getParentId() == 0) {
				//글번호가 0이면 새로작성한 글이므로 그룹번호를 새로 구함
				stmtGroup = conn.createStatement();
				rsGroup = stmtGroup.executeQuery("select max(GROUP_ID) from THEME_MESSAGE");
				int maxGroupId = 0;
				if(rsGroup.next()) {
					maxGroupId = rsGroup.getInt(1);
				}
				maxGroupId++;
				theme.setGroupId(maxGroupId);
				theme.setOrderNo(0);
			}else {
				//답글인경우, 같은 그룹 번호 내에서의 출력 순서를 구한다.
				pstmtOrder = conn.prepareStatement("select max(order_no) from theme_message "
									+ "where parent_id = ? or theme_message_id = ?");
				pstmtOrder.setInt(1, theme.getParentId());
				pstmtOrder.setInt(2, theme.getParentId());
				rsOrder = pstmtOrder.executeQuery();
				int maxOrder = 0;
				if(rsOrder.next()) {
					maxOrder = rsOrder.getInt(1);
				}
				maxOrder ++;
				theme.setOrderNo(maxOrder);
				
			}
			
			//특정 글의답변 글인 경우 같은그룹 내에서 순서 번호를 변경
			if(theme.getOrderNo() > 0) {
				pstmtOrderUpdate = conn.prepareStatement("update theme_message set order_no = order_no + 1 "
								+ "where group_id = ? and order_no >= ?");
				pstmtOrderUpdate.setInt(1, theme.getGroupId());
				pstmtOrderUpdate.setInt(2, theme.getOrderNo());
				pstmtOrderUpdate.executeUpdate();
			}
			
			//새로운 글의 번호를 구한다.
			theme.setId(Sequencer.nextId(conn, "theme_message"));
			
			//글 삽입
			pstmtInsertMessage = conn.prepareStatement("insert into theme_message values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");																						  
			pstmtInsertMessage.setInt(1, theme.getId());
			pstmtInsertMessage.setInt(2, theme.getGroupId());
			pstmtInsertMessage.setInt(3, theme.getOrderNo());
			pstmtInsertMessage.setInt(4, theme.getLevels());
			pstmtInsertMessage.setInt(5, theme.getParentId());
			pstmtInsertMessage.setTimestamp(6, theme.getRegister());
			pstmtInsertMessage.setString(7, theme.getName());
			pstmtInsertMessage.setString(8, theme.getEmail());
			pstmtInsertMessage.setString(9, theme.getImage());
			pstmtInsertMessage.setString(10, theme.getPassword());
			pstmtInsertMessage.setString(11, theme.getTitle());
			pstmtInsertMessage.executeUpdate();
			
			pstmtInsertContent = conn.prepareStatement("insert into theme_content values (?, ?)");
			pstmtInsertContent.setInt(1, theme.getId());
			pstmtInsertContent.setCharacterStream(2, new StringReader(theme.getContent()), theme.getContent().length());
			pstmtInsertContent.executeUpdate();
			conn.commit();
		}catch(SQLException ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException ex1) {
				
			}
			throw new Exception("insert", ex);
		}finally {
			if (rsGroup != null)
                try { rsGroup.close(); } catch(SQLException ex) {} 
            if (stmtGroup != null)
                try { stmtGroup.close(); } catch(SQLException ex) {}
            if (rsOrder != null)
                try { rsOrder.close(); } catch(SQLException ex) {} 
            if (pstmtOrder != null)
                try { pstmtOrder.close(); } catch(SQLException ex) {}
            if (pstmtOrderUpdate != null)
                try { pstmtOrderUpdate.close(); } catch(SQLException ex) {}
            if (pstmtInsertMessage!= null)
                try { pstmtInsertMessage.close(); } catch(SQLException ex) {}
            if (pstmtInsertContent != null)
                try { pstmtInsertContent.close(); } catch(SQLException ex) {}
            if (conn != null)
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch(SQLException ex) {}
		}
		
	}
	
	//제목과 내용만 변경
	public void update(Theme theme) throws Exception {
		Connection conn = null;
		PreparedStatement pstmtUpdateMessage = null;
		PreparedStatement pstmtUpdateContent = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmtUpdateMessage = conn.prepareStatement("update theme_message set name = ?, email = ?, image = ?, title = ? where theme_message_id = ?");
			pstmtUpdateMessage.setString(1, theme.getName());
			pstmtUpdateMessage.setString(2, theme.getEmail());
			pstmtUpdateMessage.setString(3, theme.getImage());
			pstmtUpdateMessage.setString(4, theme.getTitle());
			pstmtUpdateMessage.setInt(5, theme.getId());
			pstmtUpdateMessage.executeUpdate();
			
			pstmtUpdateContent = conn.prepareStatement("update theme_content set content = ? where theme_message_id = ?");
			pstmtUpdateContent.setCharacterStream(1, new StringReader(theme.getContent()), theme.getContent().length());
			pstmtUpdateContent.setInt(2, theme.getId());
			pstmtUpdateContent.executeUpdate();
			
			conn.commit();
		}catch(SQLException ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException ex1) {}
		}finally {
			if (pstmtUpdateMessage != null)
                try { pstmtUpdateMessage.close(); } catch(SQLException ex) {} 
			if (pstmtUpdateContent != null)
                try { pstmtUpdateContent.close(); } catch(SQLException ex) {} 
			if (conn != null)
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch(SQLException ex) {}
		}
	}
	
	//등록된 글의 개수를 구하는 메소드
	public int count(List whereCond, Map valueMap) throws Exception {
		if(valueMap == null) {
			valueMap = Collections.EMPTY_MAP;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer(200);
			query.append("select count(*) from theme_message ");
			if(whereCond != null && whereCond.size() > 0) {
				query.append("where ");
				for(int i = 0; i < whereCond.size(); i++) {
					query.append(whereCond.get(i));
					if(i < whereCond.size() - 1) {
						query.append(" or ");
					}
				}
			}
			pstmt = conn.prepareStatement(query.toString());
			
			Iterator keyIter = valueMap.keySet().iterator();
			while(keyIter.hasNext()) {
				Integer key = (Integer)keyIter.next();
				Object obj = valueMap.get(key);
				if(obj instanceof String) {
					pstmt.setString(key.intValue(), (String) obj);
				}else if(obj instanceof Integer) {
					pstmt.setInt(key.intValue(), ((Integer) obj).intValue());
				}else if(obj instanceof Timestamp) {
					pstmt.setTimestamp(key.intValue(), (Timestamp) obj);
				}
			}
			
			rs = pstmt.executeQuery();
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			return count;
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new Exception("count", ex);
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//게시글 목록 읽어오는 메소드
	public List selectList(List whereCond, Map valueMap, int startRow, int endRow) throws Exception {
		if(valueMap == null) {
			valueMap = Collections.EMPTY_MAP;
		}
		
		Connection conn = null;
		PreparedStatement pstmtMessage = null;
		ResultSet rsMessage = null;
		
		try {
			StringBuffer query = new StringBuffer(200);
			
			query.append("select * from ( ");
			query.append("	select theme_message_id, group_id, order_no, levels, parent_id, register, name, email, image, password, title, rownum rnum from ( ");			
			query.append("		select theme_message_id, group_id, order_no, levels, parent_id, register, name, email, image, password, title ");
			query.append("		from theme_message ");
			if(whereCond != null && whereCond.size() > 0) {
				query.append("where ");
				for(int i = 0; i < whereCond.size(); i++) {
					query.append(whereCond.get(i));
					if(i < whereCond.size() - 1) {
						query.append(" or ");
					}
				}
			}
			query.append("      order by group_id desc, order_no asc ");
			query.append("   ) where rownum <= ? ");
			query.append(") where rnum >= ? ");
			
			conn = getConnection();
			
			pstmtMessage = conn.prepareStatement(query.toString());
			
			Iterator keyIter = valueMap.keySet().iterator();
			while(keyIter.hasNext()) {
				Integer key = (Integer)keyIter.next();
				Object obj = valueMap.get(key);
				if(obj instanceof String) {
					pstmtMessage.setString(key.intValue(), (String) obj);
				}else if(obj instanceof Integer) {
					pstmtMessage.setInt(key.intValue(), ((Integer) obj).intValue());
				}else if(obj instanceof Timestamp) {
					pstmtMessage.setTimestamp(key.intValue(), (Timestamp) obj);
				}
			}
			
			pstmtMessage.setInt(valueMap.size() + 1, endRow + 1);
			pstmtMessage.setInt(valueMap.size() + 2, startRow + 1);
			
			rsMessage = pstmtMessage.executeQuery();
			if(rsMessage.next()) {
				List list = new java.util.ArrayList(endRow - startRow + 1);
				
				do {
					Theme theme = new Theme();
					theme.setId(rsMessage.getInt("theme_message_id"));
					theme.setGroupId(rsMessage.getInt("group_id"));
					theme.setOrderNo(rsMessage.getInt("order_no"));
					theme.setLevels(rsMessage.getInt("levels"));
					theme.setParentId(rsMessage.getInt("parent_id"));
					theme.setRegister(rsMessage.getTimestamp("register"));
					theme.setName(rsMessage.getString("name"));
					theme.setEmail(rsMessage.getString("email"));
					theme.setImage(rsMessage.getString("image"));
					theme.setPassword(rsMessage.getString("password"));
					theme.setTitle(rsMessage.getString("title"));
					list.add(theme);
				}while(rsMessage.next());
				
				return list;
			}else {
				return Collections.EMPTY_LIST;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new Exception("count", ex);
		}finally {
			if (rsMessage != null) 
                try { rsMessage.close(); } catch(SQLException ex) {}
            if (pstmtMessage != null)
                try { pstmtMessage.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	//선택한 글 읽어오기(상세보기?)
	public Theme select(int id) throws Exception {			
		Connection conn = null;
		PreparedStatement pstmtMessage = null;
		ResultSet rsMessage = null;
		PreparedStatement pstmtContent = null;
		ResultSet rsContent = null;
		
		try {
			Theme theme = null;
			
			conn = getConnection();			
			pstmtMessage = conn.prepareStatement("select * from theme_message where theme_message_id = ?");
			pstmtMessage.setInt(1, id);
			rsMessage = pstmtMessage.executeQuery();
			
			if(rsMessage.next()) {
				theme = new Theme();
				theme.setId(rsMessage.getInt("theme_message_id"));
				theme.setGroupId(rsMessage.getInt("group_id"));
				theme.setOrderNo(rsMessage.getInt("order_no"));
				theme.setLevels(rsMessage.getInt("levels"));
				theme.setParentId(rsMessage.getInt("parent_id"));
				theme.setRegister(rsMessage.getTimestamp("register"));
				theme.setName(rsMessage.getString("name"));
				theme.setEmail(rsMessage.getString("email"));
				theme.setImage(rsMessage.getString("image"));
				theme.setPassword(rsMessage.getString("password"));
				theme.setTitle(rsMessage.getString("title"));
				
				pstmtContent = conn.prepareStatement("select content from theme_content where theme_message_id = ?");
				pstmtContent.setInt(1, id);
				rsContent = pstmtContent.executeQuery();
				if(rsContent.next()) {
					Reader reader = null;
					
					try {
						reader = rsContent.getCharacterStream("content");
						char[] buff = new char[512];
						int len = -1;
						StringBuffer buffer = new StringBuffer(512);
						while((len = reader.read(buff)) != -1) {
							buffer.append(buff, 0, len);
						}
						theme.setContent(buffer.toString());
					}catch(IOException iex) {
						throw new Exception("select", iex);
					}finally {
						if(reader != null) {
							try {
								reader.close();
							}catch(IOException iex) {
								
							}
						}
					}
				}else {
					return null;
				}
				return theme;
			}else {
				return null;
			}			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new Exception("count", ex);
		}finally {
			if (rsMessage != null) 
				try { rsMessage.close(); } catch(SQLException ex) {}
			if (pstmtMessage != null)
				try { pstmtMessage.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	//게시글 삭제 메소드
	public void delete(int id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmtMessage = null;
		PreparedStatement pstmtContent = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmtMessage = conn.prepareStatement("delete from theme_message where theme_message_id = ?");
			pstmtMessage.setInt(1, id);
			
			pstmtContent = conn.prepareStatement("delete from theme_content where theme_message_id = ?");
			pstmtContent.setInt(1, id);
			
			int updatedCount1 = pstmtMessage.executeUpdate();
			int updatedCount2 = pstmtContent.executeUpdate();
			
			if(updatedCount1 + updatedCount2 == 2) {//두 테이블에서 정상적으로 삭제시 1을 반환하여 합이 2이면 두테이블이 정상삭제됨
				conn.commit();
			}else {
				conn.rollback();
				throw new Exception("invalid id: " + id);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException ex1) {
				
			}
			throw new Exception("delete", ex);
		}finally {
			if (pstmtContent != null) 
				try { pstmtContent.close(); } catch(SQLException ex) {}
			if (pstmtMessage != null)
				try { pstmtMessage.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	//read.jsp에서 코멘트 달기
	public void insertContent(int id, int level, String content, String writer) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into theme_contentt values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, level);
			pstmt.setString(3, content);
			pstmt.setString(4, writer);
			pstmt.executeUpdate();
			
			conn.commit();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	//코멘트 갯수
	public int selectContent(int id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = getConnection();
			
			String sql = "select count(*) from theme_contentt where theme_message_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count =  rs.getInt(1);
			}
			
			return count;
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	
	public int selectReflevel(int id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int ref_level = 0;
		
		try {
			conn = getConnection();
			
			String sql = "select max(ref_level) from theme_contentt where theme_message_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref_level =  rs.getInt(1);
			}
			
			return ref_level;
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	//코멘트 가져오기
	public Theme selectContentt(int id, int ref_level) throws Exception {			
		Connection conn = null;
		PreparedStatement pstmtMessage = null;
		ResultSet rsMessage = null;
		
		try {
			Theme theme = null;
			
			conn = getConnection();
			String sql = "select tm.theme_message_id, tc.ref_level, tc.content, tc.writer"
					+ "  from theme_message tm"
					+ "  inner join theme_contentt tc on tm.theme_message_id = tc.theme_message_id"
					+ "  where tm.theme_message_id = ? and tc.ref_level = ?";
			pstmtMessage = conn.prepareStatement(sql);
			pstmtMessage.setInt(1, id);
			pstmtMessage.setInt(2, ref_level);
			rsMessage = pstmtMessage.executeQuery();
			
			if(rsMessage.next()) {
				theme = new Theme();
				theme.setId(rsMessage.getInt("theme_message_id"));
				theme.setRef_level(rsMessage.getInt("ref_level"));				
				theme.setContentt(rsMessage.getString("content"));
				theme.setWriter(rsMessage.getString("writer"));				
				
				return theme;
			}else {
				return null;
			}			
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new Exception("count", ex);
		}finally {
			if (rsMessage != null) 
				try { rsMessage.close(); } catch(SQLException ex) {}
			if (pstmtMessage != null)
				try { pstmtMessage.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	//코멘트 삭제하기
	public void deletecontentt(int id, int ref_level) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "delete from theme_contentt where theme_message_id = ? and ref_level = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, ref_level);
			pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}

}
