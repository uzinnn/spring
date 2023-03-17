package kh.spring.s02.board.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int  boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String boardOriginalFilename;
	private String boardRenameFilename;
//	private BoardFileVo boardMainFile;
	private Date boardDate;
	private int boardLevel;
	private int boardRef;
	private int boardReplySeq;
	private int boardReadcount;
	
	private List<BoardFileVo> boardFileList;
	
	
}
