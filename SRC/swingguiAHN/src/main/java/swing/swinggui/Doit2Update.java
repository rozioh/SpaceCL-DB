package swing.swinggui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Doit2Update {
	
	public static void main(String[] args) {
		Doit2Update doit2Update = new Doit2Update();
		
		DoitBean doitBean = new DoitBean();
		doitBean.setCol1("104");
		doitBean.setCol4("1.0");
		
		doit2Update.update(doitBean);
		
	} // end main
	
	public void update(DoitBean updBean) {
		
		Connection conn = null;
		// insert, update, delete
		PreparedStatement pstmt = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC Mysql Driver (pom.xml에서 받은 것)
			
			// 2. 연결하기
			String url = "jdbc:mysql://localhost/studydb1";
			conn = DriverManager.getConnection(url, "spacecl", "1234"); // id, pw로 DB 연결
			System.out.println("디비 연결 성공!!!");
			
			// 3. 쿼리 준비
			String sql = "UPDATE doit2 SET col4 = ? WHERE col1 = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 데이터 binding
			pstmt.setString(1, updBean.getCol4());
			pstmt.setString(2, updBean.getCol1());
			
			// 5. 쿼리 실행
			int cnt = pstmt.executeUpdate();
			// update 된 row 수
			if(cnt == 0) {
				System.out.println("데이터 수정 실패");
			}else {
				System.out.println("데이터 수정 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // end update method
} // end class
