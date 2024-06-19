package swing.swinggui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Doit2Select {
	
	public Doit2Bean select(Connection conn, String lastInsertId) throws SQLException {
		Statement stmt = null;
		Doit2Bean doit2Bean = new Doit2Bean();

		// 3. Statement 객체 생성
		stmt = conn.createStatement();
		
		// 4. 쿼리 작성
		String sql = "SELECT col1, col2, col3, col4, col5, col6, col7 FROM DOIT2";
		
		if(lastInsertId != null) {
			sql += " WHERE col1 = " + lastInsertId;
		}
		
		// 5. 쿼리 수행
		ResultSet rs = stmt.executeQuery(sql);
		
		// 6. 쿼리 출력
		while(rs.next()) {
			String col1 = rs.getString(1);
			String col2 = rs.getString(2);
			String col3 = rs.getString(3);
			String col4 = rs.getString(4);
			String col5 = rs.getString(5);
			String col6 = rs.getString(6);
			String col7 = rs.getString(7);
			
			System.out.println(col1 + "\t" + col2 + "\t" + col3 + "\t"
					 + col4 + "\t" + col5 + "\t" + col6 + "\t" + col7 );
			
			if(lastInsertId != null) {
				doit2Bean.setCol1(col1);
				doit2Bean.setCol2(col2);
				doit2Bean.setCol3(col3);
				doit2Bean.setCol4(col4);
				doit2Bean.setCol5(col5);
				doit2Bean.setCol6(col6);
				doit2Bean.setCol7(col7);
			}
		} // end while
		
		return doit2Bean;
	} // end method select
} // end class
