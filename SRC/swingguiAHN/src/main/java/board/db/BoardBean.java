package board.db;

import lombok.Data;

/**
 * 테이블의 정보
 */

@Data
public class BoardBean {

	private String boardNo;
	private String title;
	private String contents;
	private String secretYn;
	private String count;
	private String memberNo;
	private String regDt;
}
