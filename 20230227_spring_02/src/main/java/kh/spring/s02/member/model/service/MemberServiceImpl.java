package kh.spring.s02.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.s02.member.model.dao.MemberDao;
import kh.spring.s02.member.model.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao dao;
	
	@Override
	public int insert(MemberVo vo) throws Exception {
		
		return dao.insert(vo);
	}

	@Override
	public int update(MemberVo vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public MemberVo selectOne(String id) throws Exception {
		
		return dao.selectOne(id);
	}

	@Override
	public List<MemberVo> selectList() {
		
		return dao.selectList();
	}

}
