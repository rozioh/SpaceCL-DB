package board.db;

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
	
	// get, set
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLastLoginDt() {
		return lastLoginDt;
	}
	public void setLastLoginDt(String lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

}
