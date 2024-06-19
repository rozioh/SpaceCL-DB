package swing.swinggui;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Doit2Update {

	public void update(Connection conn, Doit2Bean updBean) throws Exception {		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE doit2 SET col2 = ? WHERE col1 = ?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, updBean.getCol2());
		pstmt.setString(2, updBean.getCol1());
		
		int cnt = pstmt.executeUpdate();
		
		if(cnt == 0) {
			System.out.println("데이터 수정 실패");
			throw new Exception("데이터 Update 실패");
		}else {
			System.out.println("데이터 입력 성공");
		}
		
	} // end method update
} // end class
