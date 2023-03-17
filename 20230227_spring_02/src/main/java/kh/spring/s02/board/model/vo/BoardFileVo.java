package kh.spring.s02.board.model.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BoardFileVo {

	// private int boardNum; 무결성원칙 없는게 좋다.
	private String originalFilename;
	private String renameFilename;
}
