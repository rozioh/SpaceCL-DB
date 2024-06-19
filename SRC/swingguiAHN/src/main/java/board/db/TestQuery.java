package board.db;

import java.util.List;

public class TestQuery {
	public static void main(String[] args) {
		MemberCRUD mCRUD = new MemberCRUD();
		
		// 회원가입
//		MemberBean mBean = new MemberBean();
//		mBean.setId("hong");
//		mBean.setPw("1234");
//		mBean.setName("홍길똥");
//		mBean.setEmail("honggil@naver.com");
//		
//		int cnt = mCRUD.insertMember(mBean);
//		if(cnt == 0) {
//			System.out.println("회원가입 실패");		
//		}else {
//			System.out.println("회원가입 성공");
//		}
		
		// 회원 리스트 취득
		List<MemberBean> list = mCRUD.getMemberList();
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				MemberBean mb = list.get(i);
				// print
				mb.printAll();
			}
		}
		
		// 회원 ID를 받아서 회원정보를 취득
		MemberBean mBean = mCRUD.getMember("hong");
		if(mBean != null) {
			mBean.printAll();
			System.out.println("ID 로 회원 조회 성공");
		}else {
			System.out.println("해당 ID가 없습니다.");
		}
		
		//회원ID, PW 받아서 회원정보 취득
		mBean = mCRUD.getFindMember("minimi", "1234");
		if(mBean != null) {
			mBean.printAll();
			System.out.println("ID, PW 로 회원 조회 성공");
		}else {
			System.out.println("조회 실패");
		}
				
		
	}// end main
}// end class
