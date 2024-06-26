package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class BoardCRUD extends CommonCRUD{
	
	//TODO board 테이블에 boardBean 값을 insert 데이터 하는 
	// 코드를 작성하시오 (=insertMember() 참고해라)
	public int insertBoard(BoardBean bBean) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			//3. 쿼리 준비
			String sql = "INSERT INTO board(title, contents, member_no) VALUES(?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 4. 데이터 binding
			pstmt.setString(1, bBean.getTitle());
			pstmt.setString(2, bBean.getContents());
			pstmt.setString(3, bBean.getMemberNo());
			
			// 5. 쿼리실행
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	} // end insert
	
	/** 
	 * 전체 게시물의 갯수를 구한다.
	 * @return
	 */
	public int getTotalListCnt(String searchWord) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			// 3. 쿼리 수행을 위한 SStatement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 4. 쿼리 작성
			String sql = "SELECT count(*) FROM board"
					+ " WHERE title like '%" + searchWord + "%'" 
					+ " OR contents like '%" + searchWord + "%'";
			
			// 5. 쿼리 수행
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				cnt = rs.getInt(1); // 컬럼이 하나밖에 없어, 그리고 1부터 시작하니까 1인거야, 전체 개수
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	/**
	 * 리스트 취득 
	 * @param pageNo 0:(최신목록 10개만 가져온다), 숫자: 해당 페이지를 목록으로 취득
	 * @return
	 */
	public List<BoardBean> getBoardList(int pageNo, String searchWord){
		
		Connection conn = getConnection();
		List<BoardBean> list = new ArrayList<BoardBean>();
		int startOffset = ((pageNo - 1) * 10); // 공식 암기
		
		try {
			// 3. 쿼리 수행을 위한 SStatement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 4. 쿼리 작성
			String sql = 
					"SELECT "
					+ " board_no, title, contents, count, secret_yn, member_no, "
					+ " (select name from member where member_no = b.member_no) memberName, "
					+ " reg_dt"
					+ " FROM board b"
					+ " WHERE title like '%" + searchWord + "%'" 
					+ " OR contents like '%" + searchWord + "%'" 
					+ " ORDER BY board_no desc "
					+ " LIMIT " + startOffset + ", 10";
			
			// 5. 쿼리 수행
			ResultSet rs = stmt.executeQuery(sql);
			
			// 6. 쿼리 실행결과 출력하기
			while(rs.next()) { // 1개의 row 씩 진행
				BoardBean bean = new BoardBean();
				bean.setBoardNo( rs.getString("board_no") );
				bean.setTitle( rs.getString("title") );
				bean.setContents( rs.getString("contents") );
				bean.setCount( rs.getString("count") );
				bean.setSecretYn( rs.getString("secret_yn") );
				bean.setMemberNo( rs.getString("member_no") );
				bean.setMemberName( rs.getString("memberName") );
				bean.setRegDt( rs.getString("reg_dt") );
				
				// add list
				list.add(bean);
				
			} // end while
		} catch (Exception e) {
			e.printStackTrace();
		} // end try~catch
		
		return list;
	} // end getBoardList
	
	public BoardBean getBoard(String boardNo) {
		Connection conn = getConnection();
		BoardBean bBean = new BoardBean();
		
		try {
			// 3. 쿼리 수행을 위한 Statement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 4. 쿼리 작성
			String sql = "SELECT "
					+ " board_no, title, contents, count, secret_yn, member_no, "
					+ " (select name from member where member_no = b.member_no) memberName, "
					+ " reg_dt"
					+ " FROM board b"
					+ " WHERE board_no = '" + boardNo + "'";
			
			// 5. 쿼리 수행
			ResultSet rs = stmt.executeQuery(sql);
			
			// 6. 쿼리 실행결과 출력하기
			if(rs.next()) {
				// 1건 조회
				bBean.setBoardNo( rs.getString("board_no") );
				bBean.setTitle( rs.getString("title") );
				bBean.setContents( rs.getString("contents") );
				bBean.setCount( rs.getString("count") );
				bBean.setSecretYn( rs.getString("secret_yn") );
				bBean.setMemberNo( rs.getString("member_no") );
				bBean.setMemberName( rs.getString("memberName") );
				bBean.setRegDt( rs.getString("reg_dt") );
				
			} // end while
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bBean;
		
	}

	/**
	 * 1건 업데이트
	 * @param boardBean
	 * @return 업데이트된 row 수
	 */
	public int updateBoard(BoardBean boardBean) {
		
		int cntRow = 0;
		Connection conn = getConnection();
		
		// 값이 안오면 바로 리턴
		if( StringUtils.isEmpty(boardBean.getBoardNo())) {
			return cntRow;
		}
		try {
			// 3. 쿼리준비
			String sql = "UPDATE board SET reg_dt = now() ";
			if( StringUtils.isNotEmpty(boardBean.getBoardNo()) ) {
				sql += ", title = '" + boardBean.getTitle() + "'";
			}
			if( StringUtils.isNotEmpty(boardBean.getBoardNo()) ) {
				sql += ", contents = '" + boardBean.getContents() + "'";
			}
			sql += ", count = count + 1";
			sql += " WHERE board_no = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 4. 데이터 binding
			pstmt.setString(1,  boardBean.getBoardNo());
			
			// 5. 쿼리실행
			cntRow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cntRow;
	} // end updateBoard()
	
	/**
	 * 1건 삭제
	 * @param boardNo
	 * @return 삭제된 row 수
	 */
	public int delBoard(String boardNo) {
		
		int cntRow = 0;
		Connection conn = getConnection();
		
		// 값이 안오면 바로 리턴
		if( StringUtils.isEmpty(boardNo)) {
			return cntRow;
		}
		
		try {
			// 3. 쿼리준비
			String sql = "DELETE FROM board WHERE board_no = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 4. 데이터 binding
			pstmt.setString(1, boardNo);
			
			// 5. 쿼리실행
			cntRow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cntRow;
		
	} // end delBoard()
	
};// end class

