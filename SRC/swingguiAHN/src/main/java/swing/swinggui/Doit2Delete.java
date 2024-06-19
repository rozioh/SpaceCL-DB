package swing.swinggui;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Doit2Delete {
	public void delete(Connection conn, Doit2Bean delBean) throws Exception {
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM doit2 where col1 = ?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, delBean.getCol1());
		
		int cnt = pstmt.executeUpdate();
		
		if(cnt == 0) {
			System.out.println("데이터 삭제 실패");
			throw new Exception("데이터 Delete 실패");
		}else {
			System.out.println("데이터 삭제 성공");
		}
	} // end method delete
} // end class
