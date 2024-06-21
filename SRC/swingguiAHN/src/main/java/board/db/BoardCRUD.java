package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
			
			System.out.println("Board Data Insert 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

}
