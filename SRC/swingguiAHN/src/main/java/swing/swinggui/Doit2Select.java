package swing.swinggui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doit2Select {
	public static void main(String[] args) {
		Doit2Select doit2Select = new Doit2Select();
		
		doit2Select.select();
	} // end main
	
	public void select() {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC Mysql Driver (pom.xml에서 받은 것)
			
			// 2. 연결하기
			String url = "jdbc:mysql://localhost/studydb1";
			conn = DriverManager.getConnection(url, "spacecl", "1234"); // id, pw로 DB 연결
			System.out.println("디비 연결 성공!!!");
			
			// 3. 쿼리 수행을 위한 Statement 객체 생성
			stmt  = conn.createStatement();
			
			// 4. 쿼리 작성
//			String sql = "select * from doit2";
			String sql = "select col1, col2, col3, col4, col5, col6, col7 from doit2";
			
			// 5. 쿼리 수행
			ResultSet rs = stmt.executeQuery(sql);
			
			// 6. 쿼리 실행결과 출력하기
			while(rs.next()) { // 가져온 row 갯수만큼 돈다.				
				String col1 = rs.getString("col1");
				String col2 = rs.getString("col2");
				String col3 = rs.getString("col3");
				String col4 = rs.getString("col4");
				String col5 = rs.getString("col5");
				String col6 = rs.getString("col6");
				String col7 = rs.getString("col7");
				
				
				System.out.println(
						col1 + ", " + col2 + ", " + col3 + ", " +
						col4 + ", " + col5 + ", " + col6 + ", " + col7
				);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
