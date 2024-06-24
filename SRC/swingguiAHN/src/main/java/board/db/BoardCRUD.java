package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardCRUD extends CommonCRUD{
	
	public static void main(String[] args) {
		new BoardCRUD().getBoardList(0);
	}
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
			
			System.out.println("Board Data Insert 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	} // end insert
	
	/**
	 * 리스트 취득 
	 * @param pageNo 0:(최신목록 10개만 가져온다), 숫자: 해당 페이지를 목록으로 취득
	 * @return
	 */
	public List<BoardBean> getBoardList(int pageNo){
		Connection conn = getConnection();
		List<BoardBean> list = new ArrayList<BoardBean>();
		
		try {
			// 3. 쿼리 수행을 위한 SStatement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 4. 쿼리 작성
			String sql = 
					"select "
					+" board_no, title, contents, count, secret_yn, member_no, "
					+" (select name from member where member_no = b.member_no) memberName, "
					+" reg_dt "
					+ "from board b "
					+ "order by board_no desc "
					+ "limit 10";
			
			System.out.println(sql);
			
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

};// end class
