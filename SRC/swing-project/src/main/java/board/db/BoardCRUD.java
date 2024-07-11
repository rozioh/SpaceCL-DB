package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardCRUD extends CommonCRUD{

	/**
	 * 게시글 리스트 가져오기
	 * @param pageNo
	 * @param txtSearch
	 * @return
	 */
	public List<BoardBean> getBoardList(int pageNo, String txtSearch){
		Connection conn = getConnection();
		List<BoardBean> list = new ArrayList<BoardBean>();
		int startOffSet = (pageNo - 1) * 25;
		
		try {
			// Statement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 쿼리 작성
			String sql = "SELECT board_no, title, contents, secret_yn, count, member_no,"
					+ " (SELECT name FROM member WHERE board.member_no = member_no) MemberName,"
					+ " reg_dt"
					+ " FROM board"
					+ " WHERE title like '%" + txtSearch + "' or contents like '%" + txtSearch + "%'"
					+ " ORDER BY board_no DESC"
					+ " LIMIT " + startOffSet + ", 25";
			
			// 쿼리 수행
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardBean bBean = new BoardBean();
				bBean.setBoardNo(rs.getString(1));
				bBean.setTitle(rs.getString(2));
				bBean.setContents(rs.getString(3));
				bBean.setSecretYn(rs.getString(4));
				bBean.setCount(rs.getString(5));
				bBean.setMemberNo(rs.getString(6));
				bBean.setMemberName(rs.getString(7));
				bBean.setRegDt(rs.getString(8));
				
				list.add(bBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 게시글 저장
	 * @param bBean
	 * @return
	 */
	public int insertBoard(BoardBean bBean) {
		Connection conn = getConnection();
		int cnt = 0;
	
		try {
			// 쿼리 생성
			String sql = "INSERT INTO board(title, contents, member_no)"
					+ "VALUES(?, ?, ?)";
			
			// PreparedStatement 객체 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 쿼리 실행
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	/**
	 * 게시글 수정
	 * @param bBean
	 * @return
	 */
	public int updateBoard(BoardBean bBean) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			// 쿼리 생성
			String sql = "UPDATE board SET reg_dt = now(),"
					+ " count = count + 1,"
					+ " title = '" + bBean.getTitle() + "',"
					+ " content = '" + bBean.getContents() + "'"
					+ " WHERE board_no = '" + bBean.getBoardNo() + "'";
			
			// PreparedStatement 객체 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 쿼리 실행
			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * 게시글 삭제
	 * @param boardNo
	 * @return
	 */
	public int deleteBoard(String boardNo) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			// 쿼리 생성
			String sql = "DELETE FROM board WHERE board_no = '" + boardNo + "'";
			
			// PreparedStatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 쿼리 실행
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
		
	}
	
	/**
	 * boardNo를 통해 게시글 보기(더블클릭)
	 * @param boardNo
	 * @return
	 */
	public BoardBean getBoard(String boardNo) {
		Connection conn = getConnection();
		BoardBean bBean = new BoardBean();
		
		try {
			// Statement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 쿼리 생성
			String sql = "SELECT board_no, title, contents, secret_yn, count, member_no,"
					+ " (SELECT name FROM member WHERE board.member_no = member_no) memberName,"
					+ " reg_dt"
					+ " FROM board"
					+ " WHERE board_no = '" + boardNo + "'";
			
			// 쿼리 실행
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				bBean.setBoardNo(rs.getString("board_no"));
				bBean.setTitle(rs.getString("title"));
				bBean.setContents(rs.getString("contents"));
				bBean.setSecretYn(rs.getString("secret_yn"));
				bBean.setCount(rs.getString("count"));
				bBean.setMemberName((rs.getString("memberName")));
				bBean.setRegDt(rs.getString("reg_dt"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bBean;
	}
	
	/**
	 * 전체 게시글 개수 조회
	 * 검색조건 추가
	 * @return
	 */
	public int getTotalBoardCnt(String txtSearch) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			// Statement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 쿼리 생성
			String sql = "SELECT count(*) FROM board"
					+ " WHERE title like '%" + txtSearch + "%'"
					+ " OR contents like '%" + txtSearch + "%'";
			
			// 쿼리 실행
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
