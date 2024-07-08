package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	/**
	 * 로그인 정보 확인
	 * @param id
	 * @param pw
	 * @return
	 */
	public int getFindMember(String id, String pw) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			// Statement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 쿼리 작성
			String sql = "SELECT count(*) FROM member"
					+ " WHERE id = '" + id + "'"
					+ " and pw = '" + pw + "'";
			
			// 쿼리 실행
			ResultSet rs = stmt.executeQuery(sql);
			
			// rs 객체로부터 데이터 조회
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
