package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberCRUD extends CommonCRUD {
	
	/**
	 * 회원을 추가한다
	 * @param conn
	 * @param mBean
	 * @return int 추가 여부(1:추가, 0:실패)
	 */
	public int insertMember(MemberBean mBean) {
		Connection conn = getConnection();
		int cnt = 0;
		
		try {
			//3.쿼리준비
//			String sql = "INSERT INTO member(id, pw, name, email, birthdate, hp, addr) VALUES(?, ?, ?, ?, ?, ?, ?)";
			String sql = "INSERT INTO member(id, pw, name, email) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//4.데이터 binding
			pstmt.setString(1, mBean.getId() );
			pstmt.setString(2, mBean.getPw());
			pstmt.setString(3, mBean.getName());
			pstmt.setString(4, mBean.getEmail());
//			pstmt.setString(5, mBean.getBirthdate());
//			pstmt.setString(6, mBean.getHp());
//			pstmt.setString(7, mBean.getAddr());
			
			//5.쿼리실행 
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}; // end insertMember
	
	/**
	 * 회원 리스트를 취득한다.
	 * @return List<MemberBean>
	 */
	public List<MemberBean> getMemberList(){
		Connection conn = getConnection();
		List<MemberBean> list = new ArrayList<MemberBean>();
		
		try {
			//3.쿼리 수행을 위한 Statment 객체 생성
			Statement stmt = conn.createStatement();
			
			//4.쿼리 작성
			String sql = "select member_no, id, pw, name, email, birthdate, hp, addr, last_login_dt, reg_dt";
			sql += " from member";
						
			//5.쿼리수행
			ResultSet rs = stmt.executeQuery(sql);
			
			// 6. 쿼리 실행결과 출력하기
			while(rs.next()) { //1개의 row 씩 진행
				MemberBean bean = new MemberBean();
				bean.setMemberNo( rs.getString("member_no") );
				bean.setId( rs.getString("id") );
				bean.setPw( rs.getString("pw") );
				bean.setName( rs.getString("name") );
				bean.setEmail( rs.getString("email") );
				bean.setBirthdate( rs.getString("birthdate") );
				bean.setHp( rs.getString("hp") );
				bean.setAddr( rs.getString("addr") );
				bean.setLastLoginDt( rs.getString("last_login_dt") );
				bean.setRegDt( rs.getString("reg_dt") );
				
				// add list
				list.add(bean);
				
			} // end while
			
		} catch (Exception e) {
			e.printStackTrace();
		} // end try~catch
		return list;
		
	} // end getMemberList
	
	/**
	 * TODO 회원 ID를 받아서 회원정보를 취득하는 메서드를 작성하시오!
	 * @param id
	 * @return MemberBean
	 */
	public MemberBean getMember(String id) {
		Connection conn = getConnection();
		MemberBean mBean = new MemberBean();
		
		try {
			//3.쿼리 수행을 위한 Statment 객체 생성
			Statement stmt = conn.createStatement();
			
			//4.쿼리 작성
			String sql = "SELECT member_no, id, pw, name, email, birthdate, hp, addr, last_login_dt, reg_dt";
			sql += " FROM member WHERE id = '" + id + "'";
						
			//5.쿼리수행
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				mBean.setMemberNo( rs.getString("member_no") );
				mBean.setId( rs.getString("id") );
				mBean.setPw( rs.getString("pw") );
				mBean.setName( rs.getString("name") );
				mBean.setEmail( rs.getString("email") );
				mBean.setBirthdate( rs.getString("birthdate") );
				mBean.setHp( rs.getString("hp") );
				mBean.setAddr( rs.getString("addr") );
				mBean.setLastLoginDt( rs.getString("last_login_dt") );
				mBean.setRegDt( rs.getString("reg_dt") );
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회 안됨");
		}
		
		return mBean;
	} // end getMember
	
	/**
	 * TODO: ID, PW로 해당 멤버를 찾는 로직을 구현하시오. (안하면 화장실 3시간 못감)
	 * @param id
	 * @param pw
	 * @return MemberBean
	 */
	public MemberBean getFindMember(String id, String pw) {
		Connection conn = getConnection();
		MemberBean mBean = new MemberBean();
		
		try {
			//3.쿼리 수행을 위한 Statment 객체 생성
			Statement stmt = conn.createStatement();
			
			//4.쿼리 작성
			String sql = "SELECT member_no, id, pw, name, email, birthdate, hp, addr, last_login_dt, reg_dt";
			sql += " FROM member WHERE id = '" + id + "'" + " and pw = '" + pw + "'";
						
			//5.쿼리수행
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				mBean.setMemberNo( rs.getString("member_no") );
				mBean.setId( rs.getString("id") );
				mBean.setPw( rs.getString("pw") );
				mBean.setName( rs.getString("name") );
				mBean.setEmail( rs.getString("email") );
				mBean.setBirthdate( rs.getString("birthdate") );
				mBean.setHp( rs.getString("hp") );
				mBean.setAddr( rs.getString("addr") );
				mBean.setLastLoginDt( rs.getString("last_login_dt") );
				mBean.setRegDt( rs.getString("reg_dt") );
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회 안됨");
		}
		
		
		return mBean;
	}
}
