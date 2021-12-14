package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connection.ConnectionProvider;
import loader.JdbcUtil;
import model.Article;

public class ArticleDao {

	private static ArticleDao instance = new ArticleDao();
	
	public static ArticleDao getInstance() {
		return instance;
	}
	
	private ArticleDao() {
		
	}
	
	//게시글의 전체 개수를 구한다
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from article");
			rs.next();
			
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(rs);
		}
	}
	
	//검색기능을 위한 selectCount 오버로딩
	public int selectCount(int requestPageNumber, int n, String searchKeyword) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		String[] column_name = {"writer_name", "title", "content"}; 
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement("select count(*) from article where " + column_name[n] + " like '%'||?||'%'");
			pstmt.setString(1, searchKeyword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return x;
	}
	
	public List<Article> select(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title from ( "
					+ "select rownum rnum, article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title from ( "
					+ "select * from article m order by m.sequence_no desc "
					+ ") where rownum <= ? "					
					+ ") where rnum >= ?");
			
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, firstRow);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return Collections.emptyList();
			}
			List<Article> articleList = new ArrayList<Article>();
			do {
				Article article = makeArticleFromResultSet(rs, false);
				articleList.add(article);
			}while(rs.next());
			
			return articleList;
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	//검색 기능을위한 select오버로딩
	public List<Article> select(int firstRow, int endRow, int n, String searchKeyword) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String[] column_name = {"writer_name", "title", "content"};
		
		try {
			conn = ConnectionProvider.getConnection();
			String sql = "select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title from ( "
					+ "select rownum rnum, article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title from ( "
					+ "select * from article m where " + column_name[n] + " like '%'||?||'%' order by m.sequence_no desc "
					+ ") where rownum <= ? "					
					+ ") where rnum >= ?";
			pstmt = conn.prepareStatement(sql);			
			
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, firstRow);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return Collections.emptyList();
			}
			List<Article> articleList = new ArrayList<Article>();
			do {
				Article article = makeArticleFromResultSet(rs, false);
				articleList.add(article);
			}while(rs.next());
			
			return articleList;
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	//select메소드의 ResultSet로부터 데이터를 읽어와 Article 객체를 생성후 리턴해줌
	public Article makeArticleFromResultSet(ResultSet rs, boolean readContent) throws SQLException{
		Article article = new Article();
		article.setId(rs.getInt("article_id"));
		article.setGroupId(rs.getInt("group_id"));
		article.setSequenceNumber(rs.getString("sequence_no"));
		article.setPostingDate(rs.getTimestamp("posting_date"));
		article.setReadCount(rs.getInt("read_count"));
		article.setWriterName(rs.getString("writer_name"));
		article.setPassword(rs.getString("password"));
		article.setTitle(rs.getString("title"));
		if(readContent) {
			article.setContent(rs.getString("content"));
		}
		
		return article;
	}
	
	public int insert(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into article "
					+ "(article_id, group_id, sequence_no, posting_date, read_count, "
					+ "writer_name, password, title, content) "
					+ "values (article_id_seq.NEXTVAL, ?, ?, ?, 0, ?, ?, ?, ?)");
			pstmt.setInt(1, article.getGroupId());				
			pstmt.setString(2, article.getSequenceNumber());				
			pstmt.setTimestamp(3, new Timestamp(article.getPostingDate().getTime()));				
			pstmt.setString(4, article.getWriterName());				
			pstmt.setString(5, article.getPassword());				
			pstmt.setString(6, article.getTitle());				
			pstmt.setString(7, article.getContent());
			int insertedCount = pstmt.executeUpdate();
					
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select article_id_seq.CURRVAL from dual");
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
			
			return -1;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	//list.jsp에서 글 제목 클릭시 read.jsp로 넘어갈때 게시글 읽는 메소드 
	public Article selectById(Connection conn, int articleId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from article "
					+ "where article_id = ?");
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return null;
			}
			Article article = makeArticleFromResultSet(rs, true);
			return article;
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	//read.jsp로 게시글하나 클릭시 조회수 올려주는 메소드
	public void increaseReadCount(Connection conn, int articleId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("update article "
					+ "set read_count = read_count + 1 "
					+ "where article_id = ?");
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);			
		}
	}
	
	//등록한 부모 게시글의 마지막 자식 게시글의 순서 번호를 구함
	public String selectLastSequenceNumber(Connection conn, String searchMaxSeqNum, String searchMinSeqNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select min(sequence_no) from article "
					+ "where sequence_no < ? and sequence_no >= ?");
			pstmt.setString(1, searchMaxSeqNum);
			pstmt.setString(2, searchMinSeqNum);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			return rs.getString(1);
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	//게시글 수정
	public int update(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update article "
					+ "set title = ?, content = ? where article_id = ?");
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getId());
			
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	//게시글 삭제
	public void delete(Connection conn, int articleId) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("delete from article "
						+ "where article_id = ?");
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
					
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
}
