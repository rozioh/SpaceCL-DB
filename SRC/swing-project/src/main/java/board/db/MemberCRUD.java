package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MemberCRUD extends CommonCRUD{

	/**
	 * 회원 가입
	 * @param mBean
	 * @return
	 */
	public int insertMember(MemberBean mBean) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			// 쿼리 작성
			String sql = "INSERT INTO member(id, pw, name, email) values(?, ?, ?, ?)";
			
			// PreparedStatement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 데이터 바인딩
			pstmt.setString(1, mBean.getId());
			pstmt.setString(2, mBean.getPw());
			pstmt.setString(3, mBean.getName());
			pstmt.setString(4, mBean.getEmail());
			
			// 쿼리 실행
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
