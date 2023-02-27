package kh.spring.s02.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.s02.board.model.dao.BoardDao;
import kh.spring.s02.board.model.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao dao;
	
	@Override
	public int insert(BoardVo vo) {
		return dao.insert(vo);
	}

	@Override
	public int update(BoardVo vo) {
		
		return dao.update(vo);
	}

//	@Override
//	public int updateForReply(int boardNum) {
//		
//		return dao.updateForReply(boardNum);
//	}

	@Override
	public int delete(int boardNum) {
		
		return dao.delete(boardNum);
	}

	@Override
	public BoardVo selectOne(int boardNum, String writer) {
		BoardVo result = dao.selectOne(boardNum);
		if(!result.getBoardWriter().equals(writer)) {
			dao.updateReadCount(boardNum);
		}
//		if(dao.updateReadCount(boardNum)>0) {
//			return dao.selectOne(boardNum);
//		}else {
//			return null;
//		}
		return result;
	}

	@Override
	public List<BoardVo> selectList() {
		
		return dao.selectList();
	}

	@Override
	public int selectOneCount() {
		
		return dao.selectOneCount();
	}
	
	
}
