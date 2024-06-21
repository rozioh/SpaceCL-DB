package board.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import board.db.MemberBean;
import board.db.MemberCRUD;

public class Login extends JFrame {


	public static void main(String[] args) {
		Login log = new Login();
		
	}

	private TextField mTxtId;
	private JPasswordField mTxtPw;
	private MemberCRUD mMemCRUD = new MemberCRUD();
	
	// 생성자
	public Login() {
		
		setSize(180, 100);
		
		// 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		// center
		Panel centerP = new Panel();
		centerP.setLayout(new GridLayout(2, 2));
		
		// Label(ID)
		Label lblId = new Label("ID: ");
		//Label(PW)
		Label lblPw = new Label("PW: ");
		// Input(ID)
		mTxtId = new TextField();
		// Input(PW)
		mTxtPw = new JPasswordField();
		
		// 패널에 추가
		centerP.add(lblId);
		centerP.add(mTxtId);
		centerP.add(lblPw);
		centerP.add(mTxtPw);
		
		
		// south
		Button btnLogin = new Button("OK");
		btnLogin.addActionListener(mBtnLogin);
		
		// 레이아웃 배치
		container.add(centerP, BorderLayout.CENTER);
		container.add(btnLogin, BorderLayout.SOUTH);
		
		setVisible(true);
	};// end 생성자
	
	private ActionListener mBtnLogin = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
//			JOptionPane.showMessageDialog(null, "로그인 버튼 클릭");
			
			String id = mTxtId.getText();
			String pw = new String(mTxtPw.getPassword());

			// DB에서 확인작업
			boolean isFind = mMemCRUD.getFindMember(id, pw);
			if(isFind) {
				JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다.");
			
				// 멤버정보 조회
				MemberBean memberBean = mMemCRUD.getMember(id);
				MainBoard2 board = new MainBoard2(memberBean);
				board.setVisible(true);
				// 로그인 화면 숨김
				Login.this.setVisible(false);
				
			}else{
				JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
			}
			
		}

	};
	
}// end class
