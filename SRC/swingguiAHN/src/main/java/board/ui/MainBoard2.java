package board.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import board.db.BoardBean;
import board.db.BoardCRUD;
import board.db.MemberBean;

public class MainBoard2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnWrite;

	private MemberBean mMemBean;
	private JPanel pnlTable;
	private JTable boardTable;
	private BoardCRUD mBoardCRUD = new BoardCRUD();
	private JPanel pnlDispPage;
	
	// 현재 페이지 번호를 저장하는 변수
	public int mCurPageNo = 1;
	

	/**
	 * Create the frame.
	 */
	public MainBoard2(MemberBean memberBean) {
		mMemBean = memberBean; // 로그인 정보 들고 옴
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		txtSearch = new JTextField();
		panel.add(txtSearch);
		txtSearch.setColumns(30);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnSearch);
		
		btnWrite = new JButton("글쓰기");
		
		// 익명 클래스!
		btnWrite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				if () {
//					
//				}
				BoardWriteModal dialog = new BoardWriteModal(mMemBean, MainBoard2.this);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		panel.add(btnWrite);
		
		// 페이징
		JPanel pnlPaging = new JPanel();
		contentPane.add(pnlPaging, BorderLayout.SOUTH);
		
		btnNewButton_1 = new JButton("이전");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnlPaging.setLayout(new BorderLayout(0, 0));
		
		
		pnlPaging.add(btnNewButton_1, BorderLayout.WEST);
		
		pnlDispPage = new JPanel();
		pnlPaging.add(pnlDispPage, BorderLayout.CENTER);
		

		btnNewButton = new JButton("다음");
		pnlPaging.add(btnNewButton, BorderLayout.EAST);
		
		pnlTable = new JPanel();
		contentPane.add(pnlTable, BorderLayout.CENTER);
		
		// 리스트를 읽어온다.
		showTable(mCurPageNo);
		
		// 검색 버튼 클릭 이벤트 등록
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 리스트를 읽어온다.
				showTable(mCurPageNo); // 시작할 때 1페이지를 읽어옴
				
			}
		});
	}; // end 생성자
	
	// 리스트 출력
	public void showTable(int pageNo) {
		// DB 조회
		List<BoardBean> boardList = mBoardCRUD.getBoardList(pageNo, txtSearch.getText());
		
		// 페이징 표시
		pnlDispPage.removeAll(); // 기존 페이지 번호는 전체 삭제
		// 추가
		int listTotCnt = mBoardCRUD.getTotalListCnt( txtSearch.getText() );
		// 전체 페이지 갯수
		int totPageCnt = (int) Math.ceil(listTotCnt / 10.0) ;
		// 전체 페이지 갯수만큼 돌면서 라벨을 추가한다.
		
		for(int i = 1; i <= totPageCnt; i++) {
			SpaceCLButton btnPage;
			if(pageNo == i) {
				// 현재 페이지 표시방법
				btnPage = new SpaceCLButton("[" + i + "]");
			}else {
				btnPage = new SpaceCLButton(i + "");
				
			}
			// TODO 페이지 클릭 이벤트 showTable(해당 페이지 번호)
			// TODO 여기다 코딩
			
			btnPage.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
//					System.out.println(((SpaceCLButton) e.getSource()).getLabel()) ;					
					
					if( e.getSource() instanceof SpaceCLButton ) {
						SpaceCLButton btn = (SpaceCLButton)e.getSource();
						String title = btn.getLabel();
						System.out.println("클릭한 페이지 번호: " + title);
					 
						// 현재 페이지일 경우 그냥 return;
						if(title.startsWith("[")) {
							return;
						}
						
						int page = Integer.parseInt(title);
						showTable(page);
					}
					
				}
			});
			
			pnlDispPage.add(btnPage);
		}
		// 왕중요!! 다시 패널에 페이지 버튼을 그려야함.
		pnlDispPage.revalidate();
		
		//TODO 출력 
		String header[] = {"게시글 번호", "타이틀", "작성자", "조회수", "작성일" };
		String contents[][] = {};
		
		
		DefaultTableModel tableModel = new DefaultTableModel(contents, header);
		
		if(boardList != null) {
			for(int i = 0; i < boardList.size(); i++) {
				BoardBean bean = boardList.get(i);
				
				Vector<String> vector = new Vector<String>();
				vector.add( bean.getBoardNo() );
				vector.add( bean.getTitle() );
				vector.add( bean.getMemberName() );
				vector.add( bean.getCount() );
				vector.add( bean.getRegDt() );
				
				tableModel.addRow(vector);
			} // end for
		} // end if
		
		boardTable = new JTable(tableModel) {
			// 셀 편집을 못하도록 막는다.
			@Override
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		
		// 셀 값 가운데 정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		boardTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		boardTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		boardTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		boardTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		boardTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		
		// 컬럼크기
		TableColumnModel colModel = boardTable.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(30);
		colModel.getColumn(1).setPreferredWidth(200);
		colModel.getColumn(2).setPreferredWidth(30);
		colModel.getColumn(3).setPreferredWidth(10);
//		colModel.getColumn(4).setPreferredWidth(10); // 나머지 영역을 갖는다.
		
		// 스크롤 추가
		JScrollPane scrollTable = new JScrollPane(boardTable);
		scrollTable.setLocation(0, 0);
		scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//전체 가로, 세로 크기
		scrollTable.setSize(pnlTable.getWidth(), pnlTable.getHeight());
		
		//add component
		pnlTable.removeAll();
		pnlTable.setLayout(new BorderLayout(0, 0));
		pnlTable.add(scrollTable);
		
	} // end showTable()
	
}; // end class
