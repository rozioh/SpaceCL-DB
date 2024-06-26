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


public class BoardDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MemberBean mMemberBean;
	private BoardBean mBoardBean;
	private JTextField txtTitle;
	private JTextArea txtContent;
	private BoardCRUD mBoardCRUD = new BoardCRUD();
	private MainBoard2 mMainBoard2;

	/**
	 * Create the dialog.
	 */
	public BoardDetail(MemberBean memBean, BoardBean boardBean, MainBoard2 mainBoard2) {
		// 멤버객체
		mMemberBean = memBean;
		mBoardBean = boardBean;
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
				JLabel lblTitle = new JLabel("제목 : ");
				lblTitle.setPreferredSize(new Dimension(50, 50));
				lblTitle.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
				panel.add(lblTitle, BorderLayout.WEST);
			}
			{
				txtTitle = new JTextField();
				txtTitle.setFont(new Font("굴림", Font.PLAIN, 15));
				txtTitle.setColumns(10);
				panel.add(txtTitle, BorderLayout.CENTER);
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
			txtContent = new JTextArea();
			contentPanel.add(txtContent, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDel = new JButton("삭제");
				btnDel.setActionCommand("OK");
				// 삭제 버튼 클릭
				btnDel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int res = JOptionPane.showConfirmDialog(null, 
								"삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
						
						if(res == JOptionPane.YES_OPTION) {
							int cnt = mBoardCRUD.delBoard( mBoardBean.getBoardNo() ); 
							System.out.println("현재 cnt 값 : " + cnt);
							if( cnt > 0 ) {
								// 삭제완료
								JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "진짜?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
								BoardDetail.this.dispose();
								
								// 리스트 새롭게 조회
								mainBoard2.showTable( mainBoard2.mCurPageNo );
							}else {
								JOptionPane.showMessageDialog(null, "삭제 실패");
							}
						}
						// 실행로직
						
					}
				});
				{
					JButton btnUpdate = new JButton("수정");
					buttonPane.add(btnUpdate);
					
					// 수정 버튼 클릭
					btnUpdate.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							BoardBean boardBean = new BoardBean();
							boardBean.setTitle( txtTitle.getText() );
							boardBean.setContents( txtContent.getText() );
							boardBean.setMemberNo( mMemberBean.getMemberNo() );
							boardBean.setBoardNo( mBoardBean.getBoardNo());
							
							int cnt = mBoardCRUD.updateBoard(boardBean);
							if( cnt > 0 ) {
								// 수정 성공
								JOptionPane.showMessageDialog(null, "수정에 성공하였습니다.");
								BoardDetail.this.dispose();
								// 리스트 새롭게 조회
								mainBoard2.showTable( mainBoard2.mCurPageNo );
							}
							
						}
					});
				}
				buttonPane.add(btnDel);
				getRootPane().setDefaultButton(btnDel);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "확인창", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.YES_OPTION) { //YES 버튼 누르면 
							BoardDetail.this.dispose();
						}else { //0 이외의 숫자가 나오면 비정상 종료임 
							System.out.println("취소");
						}
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
		// 표시
		txtTitle.setText( mBoardBean.getTitle() );
		txtContent.setText( mBoardBean.getContents() );
		
	} // end 생성자

}
