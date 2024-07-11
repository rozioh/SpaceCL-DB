package board.db;

import lombok.Data;

@Data
public class BoardBean {
	private String boardNo;
	private String title;
	private String contents;
	private String secretYn;
	private String count;
	private String memberNo;
	private String memberName;
	private String regDt;
}
