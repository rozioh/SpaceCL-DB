package board.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import board.db.MemberCRUD;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField txtPw;
	private MemberCRUD mMemberCRUD = new MemberCRUD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		
		txtId = new JTextField();
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblPw = new JLabel("PW");
		panel.add(lblPw);
		
		txtPw = new JPasswordField();
		txtPw.addActionListener(clickLogin); // 엔터 시 로그인
		panel.add(txtPw);
		txtPw.setColumns(10);
		
		JPanel panelBtn = new JPanel();
		contentPane.add(panelBtn);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(clickLogin);
		panelBtn.add(btnLogin);
	}
	
	private ActionListener clickLogin = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = txtId.getText();
			String pw = new String(txtPw.getPassword());
			
			int cnt = mMemberCRUD.getFindMember(id, pw);
			if(cnt > 0) {
				JOptionPane.showMessageDialog(rootPane, "로그인 성공");
				dispose(); // 로그인 창 닫기
			}else {
				JOptionPane.showMessageDialog(rootPane, "로그인 실패");
			}
			
			
		}
	};

}
