package board.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import board.db.MemberBean;

public class ChatMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMsg;
	private JTextArea jtaChatMsg;
	private MemberBean mMemBean;


	/**
	 * Create the frame.
	 */
	public ChatMain(MemberBean memberBean) {
		mMemBean = memberBean;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		// 채팅메시지 출력창
		jtaChatMsg = new JTextArea();
		jtaChatMsg.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(jtaChatMsg);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		txtMsg = new JTextField();
		panel.add(txtMsg, BorderLayout.CENTER);
		txtMsg.setColumns(10);
		
		JButton btnSend = new JButton("전송");
		panel.add(btnSend, BorderLayout.EAST);
		
		btnSend.addActionListener(mSendClick);
	}; // end 생성자

	private ActionListener mSendClick = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
}
