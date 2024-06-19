package swing.swinggui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Doit2Test {
	public static void main(String[] args) {
		Doit2Select doit2Select = new Doit2Select();
		Doit2Insert doit2Insert = new Doit2Insert();
		Doit2Update doit2Update = new Doit2Update();
		Doit2Delete doit2Delete = new Doit2Delete();
		
		Doit2Bean doit2Bean = new Doit2Bean();
		
		Connection conn = null;
		
		try {
			//1.드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC Mysql Driver (pom.xml)
			
			//2.연결하기
			String url = "jdbc:mysql://localhost/studydb1";
			conn = DriverManager.getConnection(url, "spacecl", "1234"); //id, pw로 디비연결
			System.out.println("디비 연결 성공!!!");
			
			conn.setAutoCommit(false);
			
			// insert
			doit2Bean.setCol2("미니언");
			doit2Bean.setCol4("1");
			doit2Bean.setCol5("2");
			doit2Bean.setCol6("3");
			doit2Bean.setCol7("4");
			
			String lastInsertId = doit2Insert.insert(conn, doit2Bean);
			
			// select
			Doit2Bean selBean = doit2Select.select(conn, lastInsertId);
			System.out.println(selBean.getCol1() + "\t" + selBean.getCol2());
			
			// update
			doit2Bean.setCol1(lastInsertId);
			doit2Bean.setCol2("장보고 " + lastInsertId);
			doit2Update.update(conn, doit2Bean);
			
			// select
			doit2Select.select(conn, lastInsertId);
			
			// delete
			doit2Bean.setCol1(lastInsertId);
			doit2Delete.delete(conn, doit2Bean);
			
			// select
			doit2Select.select(conn, lastInsertId);
			
			conn.commit();
			System.out.println("Commit 완료");
		} catch (Exception e) {
			e.printStackTrace();
			if(conn != null) {
				try {
					conn.rollback();
					System.out.println("RollBack");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
