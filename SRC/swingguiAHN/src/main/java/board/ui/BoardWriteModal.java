package board.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import board.db.BoardBean;
import board.db.BoardCRUD;
import board.db.MemberBean;


public class BoardWriteModal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MemberBean mMemberBean;
	private BoardBean mBoardBean;
	private JTextField textField;
	private JTextArea textContentArea;
	private BoardCRUD mBoardCRUD = new BoardCRUD();
	private MainBoard2 mMainBoard2;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			BoardWriteModal dialog = new BoardWriteModal();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public BoardWriteModal(MemberBean memBean, MainBoard2 mainBoard2) {
		// 멤버객체
		mMemberBean = memBean;
		mMainBoard2 = mainBoard2;
		
		setBounds(100, 100, 591, 518);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblTitleLabel = new JLabel("제목 : ");
				lblTitleLabel.setPreferredSize(new Dimension(50, 50));
				lblTitleLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
				panel.add(lblTitleLabel, BorderLayout.WEST);
			}
			{
				textField = new JTextField();
				textField.setFont(new Font("굴림", Font.PLAIN, 15));
				textField.setColumns(10);
				panel.add(textField, BorderLayout.CENTER);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(0, 0));
		}
		{
			JLabel lblContentLabel = new JLabel("내용");
			lblContentLabel.setPreferredSize(new Dimension(50, 50));
			contentPanel.add(lblContentLabel, BorderLayout.WEST);
			lblContentLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 13));
		}
		{
			textContentArea = new JTextArea();
			contentPanel.add(textContentArea, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");		
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						mBoardBean = new BoardBean();
						
						// mBoardBean에 데이터 저장
						mBoardBean.setTitle(textField.getText());
						mBoardBean.setContents(textContentArea.getText());
						mBoardBean.setMemberNo(mMemberBean.getMemberNo());
						
						BoardCRUD bCRUD = new BoardCRUD();
						int cnt = bCRUD.insertBoard(mBoardBean);
						if( cnt > 0 ) {
							JOptionPane.showMessageDialog(null, "글쓰기 저장 성공");
							// 글쓰기 화면 숨김
							clearText();
//							BoardWriteModal.this.setVisible(false);
							BoardWriteModal.this.dispose();
							
							// TODO 리스트를 새로 읽어와야해.
							// 리스트 새롭게 조회
							mainBoard2.showTable( 0 );
						}else {
							JOptionPane.showMessageDialog(null, "글쓰기 저장 실패");
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "확인창", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.YES_OPTION) { //YES 버튼 누르면 
							BoardWriteModal.this.dispose();
						}else { //0 이외의 숫자가 나오면 비정상 종료임 
							System.out.println("취소");
						}
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	} // end 생성자
	
	// textClear
	public void clearText() {
		textField.setText(""); 
		textContentArea.setText("");
	}

}
