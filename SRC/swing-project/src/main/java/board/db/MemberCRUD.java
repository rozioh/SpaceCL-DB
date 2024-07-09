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
	 * 로그인 성공 여부 확인
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
	
	/**
	 * 로그인 id로 멤버 정보 가져오기
	 * @param id
	 * @return
	 */
	public MemberBean getMemberInfo(String id) {
		Connection conn = getConnection();
		MemberBean memberBean = new MemberBean();
		
		try {
			// Statement 객체 생성
			Statement stmt = conn.createStatement();
			
			// 쿼리 생성
			String sql = "SELECT member_no, id, pw, name, email, birthdate, hp, addr, last_login_dt, reg_dt"
					+ " FROM member"
					+ " WHERE id = '" + id + "'";
			
			// 쿼리 실행
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				memberBean.setMemberNo(rs.getString(1));
				memberBean.setId(rs.getString(2));
				memberBean.setPw(rs.getString(3));
				memberBean.setName(rs.getString(4));
				memberBean.setEmail(rs.getString(5));
				memberBean.setBirthdate(rs.getString(6));
				memberBean.setHp(rs.getString(7));
				memberBean.setAddr(rs.getString(8));
				memberBean.setRegDt(rs.getString(9));
				memberBean.setLastLoginDt(rs.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberBean;
	}
	
	/**
	 * 회원 삭제
	 * @param id
	 * @return
	 */
	public int deleteMember(String id) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			// 쿼리 작성
			String sql = "DELETE FROM member WHERE id = '" + id + "'";
			
			// PreparedStatement 객체 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 쿼리 실행
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
