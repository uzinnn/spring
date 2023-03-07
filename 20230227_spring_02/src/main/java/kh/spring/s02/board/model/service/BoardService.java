package kh.spring.s02.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kh.spring.s02.board.model.vo.BoardVo;

public interface BoardService {
	public int insert(BoardVo vo);
	public int update(BoardVo vo);
// Service 역할 - Transaction 기능  = Dao여러메소드를 하나의 기능으로 묶어서 처리함
//	public int updateForReply(int boardNum);
	public int delete(int boardNum  /* BoardVo vo 또는 PK 또는 List<PK>*/) ;
	
//	public int updateReadCount(int boardNum);
	
	public List<BoardVo> selectList();  // 전체읽기
	public List<BoardVo> selectList(int currentPage, int limit);  // paging처리하여 읽기
	public List<BoardVo> selectList(int currentPage, int limit, String searchWord);  // paging처리 +검색하여 읽기
	
	
	public int selectOneCount();
	public int selectOneCount(String searchWord);
	public BoardVo selectOne(int boardNum, String writer /* PK */) ;


	public List<BoardVo> selectReplyList(int boardNum);  // 글의 답글 전체읽기
	public List<BoardVo> selectReplyList(int boardNum,int currentPage, int limit);  //글의 답글 페이징처리해서 읽기
}
