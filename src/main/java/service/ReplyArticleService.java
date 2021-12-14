package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import connection.ConnectionProvider;
import dao.ArticleDao;
import loader.JdbcUtil;
import model.Article;
import model.ReplyingRequest;

public class ReplyArticleService {
	//답변 게시글의 순서 번호 구하는 클래스
	private static ReplyArticleService instance = new ReplyArticleService();
	
	public static ReplyArticleService getInstance() {
		return instance;
	}
	
	private ReplyArticleService() {
		
	}
	
	public Article reply(ReplyingRequest replyingRequest) throws ArticleNotFoundException, CannotReplyArticleException, LastChildAleadyExistsException {
		Connection conn = null;
		
		Article article = replyingRequest.toArticle(); //ReplyingRequest클래스에서 Article 객체를 생성함
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleDao articleDao = ArticleDao.getInstance();
			Article parent = articleDao.selectById(conn, replyingRequest.getParentArticleId()); //한개의 게시글에 대한 모든정보 가져옴
			
			try {
				checkParent(parent, replyingRequest.getParentArticleId());
			}catch(Exception e) {
				JdbcUtil.rollback(conn);
				if(e instanceof ArticleNotFoundException) {
					throw (ArticleNotFoundException) e;
				}else if(e instanceof CannotReplyArticleException) {
					throw (CannotReplyArticleException) e;
				}else if(e instanceof LastChildAleadyExistsException) {
					throw (LastChildAleadyExistsException) e;
				}
			}
			
			String searchMaxSeqNum = parent.getSequenceNumber();//자식 글의최대 순서번호
			String searchMinbSeqNum = getSearchMinSeqNum(parent);//자식 글의최소 순서번호
			
			String lastChildSeq = articleDao.selectLastSequenceNumber(conn, searchMaxSeqNum, searchMinbSeqNum);//최대,최소 내에서 가장 작은 값을 갖는 자식 글의순서 번호
			
			String sequencrNumber = getSequenceNumber(parent, lastChildSeq);//가장 작은 자식글번호를 이용해 새 자식 글의 순서 번호를 구함
			
			article.setGroupId(parent.getGroupId());
			article.setSequenceNumber(sequencrNumber);
			article.setPostingDate(new Date());
			
			int articleId = articleDao.insert(conn, article);//답 글을 테이블에 삽입
			if(articleId == -1) {
				throw new RuntimeException("DB 삽입 안됨 : " + articleId);
			}
			conn.commit();
			article.setId(articleId);
			return article;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB 작업 실패 : " + e.getMessage(), e);
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException e) {
					
				}
			}
			JdbcUtil.close(conn);
		}
	}
	
	//부모글이 올바른지의 대한 여부검사하는 메소드
	private void checkParent(Article parent, int parentId) throws ArticleNotFoundException, CannotReplyArticleException {
		if(parent == null) {
			throw new ArticleNotFoundException("부모글이 존재하지 않음 : " + parentId);
		}
		
		int parentLevel = parent.getLevel();
		if(parentLevel == 3) {
			throw new CannotReplyArticleException("마지막 레벨 글에는 답글을 달 수 없습니다 : " + parent.getId());
		}
	}
	
	private String getSearchMinSeqNum(Article parent) {
		String parentSeqNum = parent.getSequenceNumber();
		DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");
		long parentSeqLongValue = Long.parseLong(parentSeqNum);
		long searchMinLongValue = 0;
		
		switch(parent.getLevel()) {
			case 0:
				searchMinLongValue = parentSeqLongValue / 1000000L * 1000000L;
				break;
			case 1:
				searchMinLongValue = parentSeqLongValue / 10000L * 10000L;
				break;
			case 2:
				searchMinLongValue = parentSeqLongValue / 100L * 100L;
				break;
		}
		
		return decimalFormat.format(searchMinLongValue);
	}
	
	//새 자식글의 순서 번호를 구하는 메소드
	private String getSequenceNumber(Article parent, String lastChildSeq) throws LastChildAleadyExistsException {
		long parntSeqLong = Long.parseLong(parent.getSequenceNumber());
		int parentLevel = parent.getLevel();
		
		long decUnit = 0;
		if(parentLevel == 0) {
			decUnit = 10000L;
		}else if(parentLevel == 1) {
			decUnit = 100L;
		}else if(parentLevel == 2) {
			decUnit = 1L;
		}
		
		String sequenceNumber = null;
		
		DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");
		if(lastChildSeq == null) {//답변글없음
			sequenceNumber = decimalFormat.format(parntSeqLong - decUnit);
		}else {//답변글있음
			//마지막 답변 글인지 확인
			String orderOfLastChildSeq = null;
			if(parentLevel == 0) {
				orderOfLastChildSeq = lastChildSeq.substring(10, 12);
				sequenceNumber = lastChildSeq.substring(0, 12) + "9999";
			}else if(parentLevel == 1) {
				orderOfLastChildSeq = lastChildSeq.substring(12, 14);
				sequenceNumber = lastChildSeq.substring(0, 14) + "99";
			}else if(parentLevel == 2) {
				orderOfLastChildSeq = lastChildSeq.substring(14, 16);
				sequenceNumber = lastChildSeq;
			}
			
			if(orderOfLastChildSeq.equals("00")) {
				throw new LastChildAleadyExistsException("마지막 자식 글이 이미 조재합니다 : " + lastChildSeq);
			}
			long seq = Long.parseLong(sequenceNumber) - decUnit;
			sequenceNumber = decimalFormat.format(seq);
		}
		return sequenceNumber;
	}

}
