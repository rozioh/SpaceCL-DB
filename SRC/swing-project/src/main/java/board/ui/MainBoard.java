package board.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import board.db.BoardBean;
import board.db.BoardCRUD;
import board.db.MemberBean;

public class MainBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private MemberBean mMemBean;
	private BoardCRUD mBoardCRUD;
	private int mCurPageNo = 1; // 1페이지 시작
	private DefaultTableModel tableModel;
	private JTable boardTable;
	private JPanel pnlBoardTable;
	private JLabel lblTotCnt;
	private JPanel pnlDispPage;
	private int totListCnt;
	private int totPageCnt;

	/**
	 * Create the frame.
	 * @param memberBean 
	 */
	public MainBoard(MemberBean memberBean) {
		// 로그인 한 회원 정보 저장
		mMemBean = memberBean;
		// MainBoard 생성될 때 BoardCRUD 생성. DB 데이터 가져올 때 사용
		mBoardCRUD = new BoardCRUD();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// 상단 패널
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnChat = new JButton("채팅하기");
		panel.add(btnChat);
		
		txtSearch = new JTextField();
		panel.add(txtSearch);
		txtSearch.setColumns(30);
		
		JButton btnSearch = new JButton("검색");
		panel.add(btnSearch);
		
		// 하단 패널
		JPanel pnlPaging = new JPanel();
		contentPane.add(pnlPaging, BorderLayout.SOUTH);
		pnlPaging.setLayout(new BorderLayout(0, 0));
		
		JButton btnPagePrev = new JButton("이전");
		pnlPaging.add(btnPagePrev, BorderLayout.WEST);
		
		JButton btnPageNext = new JButton("다음");
		pnlPaging.add(btnPageNext, BorderLayout.EAST);
		
		// 페이지 번호 표시될 패널
		pnlDispPage = new JPanel();
		pnlPaging.add(pnlDispPage, BorderLayout.CENTER);
		
		// 게시글 패널
		pnlBoardTable = new JPanel();
		contentPane.add(pnlBoardTable, BorderLayout.CENTER);
		pnlBoardTable.setLayout(new BorderLayout(0, 0));
		
		showTable(mCurPageNo);
		
		
	}

	// 게시글 리스트
	public void showTable(int pageNo) {
		// 다른 페이지로 이동했을 때 현재 페이지를 Cur에 저장
		mCurPageNo = pageNo;
		
		// Board List 조회
		List<BoardBean> boardList = mBoardCRUD.getBoardList(pageNo, txtSearch.getText());
		
		// board 데이터 테이블에 세팅
		String header[] = {"게시글 번호" , "타이틀" , "작성자", "조회수", "작성일"};
		String contents[][] = {};
		
		tableModel = new DefaultTableModel(contents, header);
		
		if(boardList != null) {
			for(int i = 0; i < boardList.size(); i++) {
				BoardBean bBean = boardList.get(i);
				
				Vector<String> vector = new Vector<String>();
				vector.add(bBean.getBoardNo());
				vector.add(bBean.getTitle());
				vector.add(bBean.getMemberName());
				vector.add(bBean.getCount());
				vector.add(bBean.getRegDt());
				
				tableModel.addRow(vector);
			}
		}
		
		boardTable = new JTable(tableModel) {
			// 테이블 편집 제한
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// scrollPane에 board 데이터 초기화
		JScrollPane scrollPane = new JScrollPane(boardTable);
		// 새로운 게시글 갱신을 위해 필요
		pnlBoardTable.removeAll();
		pnlBoardTable.add(scrollPane);
		
		lblTotCnt = new JLabel();
		pnlBoardTable.add(lblTotCnt, BorderLayout.NORTH);
		
		// 테이블 스타일 지정
		setTableStyle(boardTable);
		
		// 페이징 처리
		showPaging(mCurPageNo);
		
		// 더블클릭 글보기
//		boardTable.addMouseListener(doubleClick);
		
	}

	/**
	 * 페이징 설정
	 * @param pageNo
	 */
	public void showPaging(int pageNo) {
		
		// 다음 페이지로 이동할 때 기존 데이터를 지우고 다시 표시해야한다.
		pnlDispPage.removeAll();
		// 전체 게시글 개수
		totListCnt = mBoardCRUD.getTotalBoardCnt(txtSearch.getText());
		// 라벨 갱신
		lblTotCnt.setText("총" + totListCnt + "개");
		// 전체 페이지 개수
		totPageCnt = (int) Math.ceil(totListCnt / 25.0);
		
		// 전체 페이지 개수를 돌면서 페이지 버튼 추가
		for(int i = 1; i <= totPageCnt; i++) {
			
			String title = "";
			Button btnPage;
			if(pageNo == i) {
				title = "[" + i + "]";
			}else {
				title = i + "";
			}
			btnPage = new Button(title);
			
			// 페이지 버튼 클릭 이벤트 추가
			btnPage.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Button btn = (Button) e.getSource();
					String title = btn.getLabel();
					
					// 현재 페이지일 경우 리턴
					if(title.startsWith("[")) {
						return;
					}else {
						int page = Integer.parseInt(title);
						// 게시글 이동
						showTable(page);
					}
				}
			});
			
			// 패널에 생성한 페이지 버튼 추가
			pnlDispPage.add(btnPage);
		}// end for
		
		// 패널에 페이지 버튼 다시 출력
		pnlDispPage.revalidate();
	}

	private void setTableStyle(JTable boardTable) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		
		// 가운데 정렬
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		// 테이블의 컬럼모델
		TableColumnModel colModel = boardTable.getColumnModel();
		
		for(int i = 0; i < 5; i++) {
			colModel.getColumn(i).setCellRenderer(dtcr);
		}
		
		// 컬럼 크기 지정
		colModel.getColumn(0).setPreferredWidth(30);
		colModel.getColumn(1).setPreferredWidth(200);
		colModel.getColumn(2).setPreferredWidth(30);
		colModel.getColumn(3).setPreferredWidth(10);
		
		// 컬럼 이동 불가
		boardTable.getTableHeader().setReorderingAllowed(false);
		// 컬럼 크기 조절 불가
		boardTable.getTableHeader().setResizingAllowed(false);
		
		
	}

}
