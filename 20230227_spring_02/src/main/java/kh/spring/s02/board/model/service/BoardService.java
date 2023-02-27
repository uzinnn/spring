package kh.spring.s02.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kh.spring.s02.board.model.vo.BoardVo;

public interface BoardService {
	public int insert(BoardVo vo);
	public int update(BoardVo vo);
//	public int updateReadCount(int boardNum) ;
//  public int updateForReply(int boardNum); 
	public int delete(int boardNum/*BoardVo vo 또는 PK 또는 List<PK>*/);
	public BoardVo selectOne(int boardNum/*PK*/, String writer);
	public List<BoardVo> selectList();
	public int selectOneCount();

}
