package board.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonCRUD {
	private static final String DB_NAME = "board";
	private Connection mConnection;
	
	public Connection getConnection() {
		
		try {
			if(mConnection == null) {
				// 드라이버 로딩
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// DB 연결
				String url = "jdbc:mysql://localhost/" + DB_NAME;
				mConnection = DriverManager.getConnection(url, "root", "1234");
			}
			System.out.println("CommonCRUD: DB 연결 성공");
			
		} catch (Exception e) {
			System.out.println("CommonCRUD: DB 연결 실패");
			e.printStackTrace();
		}
		
		return mConnection;
	}
}
