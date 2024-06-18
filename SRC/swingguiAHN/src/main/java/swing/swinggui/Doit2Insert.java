package swing.swinggui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @since 2024-06-17
 */
public class Doit2Insert {
	
	public static void main(String[] args) {
		Doit2Insert doit2Insert = new Doit2Insert();
		
		DoitBean doitBean = new DoitBean();
		doitBean.setCol2("두번째열");
		doitBean.setCol4("444");
		doitBean.setCol5("555");
		doitBean.setCol6("666");
		doitBean.setCol7("777");
		
		doit2Insert.insert(doitBean);
		
	} // end main
	
	public void insert(DoitBean insBean) {
		
		Connection conn = null;
		// insert, update, delete
		PreparedStatement pstmt = null; // Statement는 select 문에 사용
		
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC Mysql Driver (pom.xml에서 받은 것)
			
			// 2. 연결하기
			String url = "jdbc:mysql://localhost/studydb1";
			conn = DriverManager.getConnection(url, "spacecl", "1234"); // id, pw로 DB 연결
			System.out.println("디비 연결 성공!!!");
			
			// 3. 쿼리 준비
			String sql = "INSERT INTO doit2(col2, col4, col5, col6, col7) VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 데이터 binding
			pstmt.setString(1, insBean.getCol2());
			pstmt.setString(2, insBean.getCol4());
			pstmt.setString(3, insBean.getCol5());
			pstmt.setString(4, insBean.getCol6());
			pstmt.setString(5, insBean.getCol7());
			
			// 5. 쿼리 실행
			int cnt = pstmt.executeUpdate();
			// insert 된 row 수
			if(cnt == 0) {
				System.out.println("데이터 입력 실패");
			}else {
				System.out.println("데이터 입력 성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
