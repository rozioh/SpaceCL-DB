package board.db;

import lombok.Data;

@Data
public class MemberBean {
	private String memberNo;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String birthdate;
	private String hp;
	private String addr;
	private String lastLoginDt;
	private String regDt;
	
	public void printAll() {
		System.out.println( 
			memberNo + "\t" + id + "\t" + pw + "\t" + name + "\t" + 
			email + "\t" + birthdate + "\t" + hp + "\t" + addr + "\t" + 
			lastLoginDt + "\t" + regDt
		);
	}

}
