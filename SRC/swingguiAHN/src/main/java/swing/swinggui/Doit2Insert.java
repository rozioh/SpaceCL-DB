package swing.swinggui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Doit2Insert {

	public String insert(Connection conn, Doit2Bean insBean) throws Exception {
		
		String lastInsertId = "";
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO doit2(col2, col4, col5, col6, col7) VALUES(?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, insBean.getCol2());
		pstmt.setString(2, insBean.getCol4());
		pstmt.setString(3, insBean.getCol5());
		pstmt.setString(4, insBean.getCol6());
		pstmt.setString(5, insBean.getCol7());
		
		int cnt = pstmt.executeUpdate();
		
		if(cnt == 0) {
			System.out.println("데이터 입력 실패");
			throw new Exception("데이터 Insert 실패");
		}else {
			System.out.println("데이터 입력 성공");
			
			String sql2 = "SELECT last_insert_id()";
			ResultSet rs = pstmt.executeQuery(sql2);
			if(rs.next()) {
				lastInsertId = rs.getString(1);
			}
		}
		return lastInsertId;
		
	} // end method insert
} // end class
