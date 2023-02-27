package kh.spring.s02.board.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import kh.spring.s02.board.model.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(BoardVo vo) {
	
		return sqlSession.insert("boardns.insertid",vo);
	}
	
	public int update(BoardVo vo) {
		return sqlSession.insert("boardns.updateid",vo);
	}
	public int updateReadCount(int boardNum) {
		return sqlSession.insert("boardns.updateReadCount",boardNum);
	}
	public int updateForReply(int boardNum) {
		return sqlSession.insert("boardns.updateForReply",boardNum);
	}
	public int delete(int boardNum/*BoardVo vo 또는 PK 또는 List<PK>*/) {
		return sqlSession.delete("boardns.insertid",boardNum);
	}
	public BoardVo selectOne(int boardNum/*PK*/) {
		return sqlSession.selectOne("boardns.selectOneid",boardNum);
	}
	public List<BoardVo> selectList(){
		return sqlSession.selectList("boardns.selectListid");
	}

	public int selectOneCount(){
		return sqlSession.selectOne("boardns.selectOneCountid");
	}
	
	
//	public List<BoardVo> selectList2(){
//		return sqlSession.selectList("boardns.selectListid2");
//	}
}
