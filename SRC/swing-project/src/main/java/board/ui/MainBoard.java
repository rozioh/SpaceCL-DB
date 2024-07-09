package board.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import board.db.MemberBean;

public class MainBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 * @param memberBean 
	 */
	public MainBoard(MemberBean memberBean) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnChat = new JButton("채팅하기");
		panel.add(btnChat);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(30);
		
		JButton btnSearch = new JButton("검색");
		panel.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnPagePrev = new JButton("이전");
		panel_1.add(btnPagePrev, BorderLayout.WEST);
		
		JButton btnPageNext = new JButton("다음");
		panel_1.add(btnPageNext, BorderLayout.EAST);
		
		JPanel pnlDispPage = new JPanel();
		panel_1.add(pnlDispPage, BorderLayout.CENTER);
		
		JPanel pnlBoardTable = new JPanel();
		contentPane.add(pnlBoardTable, BorderLayout.CENTER);
		pnlBoardTable.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTotCnt = new JLabel("New label");
		pnlBoardTable.add(lblTotCnt, BorderLayout.NORTH);
		
		JTextArea scrollPane = new JTextArea();
		pnlBoardTable.add(scrollPane, BorderLayout.CENTER);
	}

}
