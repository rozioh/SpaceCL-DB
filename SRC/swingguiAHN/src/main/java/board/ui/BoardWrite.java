package board.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.BoxLayout;

public class BoardWrite extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTitleField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardWrite frame = new BoardWrite();
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
	public BoardWrite() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitleLabel = new JLabel(" 제목 : ");
		lblTitleLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		panel.add(lblTitleLabel, BorderLayout.WEST);
		
		textTitleField = new JTextField();
		textTitleField.setFont(new Font("굴림", Font.PLAIN, 15));
		panel.add(textTitleField, BorderLayout.CENTER);
		textTitleField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblContentLabel_1 = new JLabel("내용");
		lblContentLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 13));
		panel_1.add(lblContentLabel_1);
		
		JTextArea textContentArea = new JTextArea();
		panel_1.add(textContentArea);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnConfirm = new JButton("확인");
		panel_2.add(btnConfirm);
		
		JButton btnCancel = new JButton("취소");
		panel_2.add(btnCancel);
	}

}
