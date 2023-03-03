package kh.spring.s02.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s02.member.model.service.MemberService;
import kh.spring.s02.member.model.vo.MemberVo;

@Controller
@RequestMapping("/memeber")
public class MemberController {
	

	@Autowired
	private MemberService service;

	@GetMapping("/signUp")
		public ModelAndView viewInsert(ModelAndView mv) {
	
		mv.setViewName("member/signUp");
		
		return mv;
	}
	
	// @PostMapping("/signUp")	
	
	@GetMapping("/testSignUp")
	public ModelAndView insert(ModelAndView mv,MemberVo vo) {
		vo.setEmail("emailddd");
		vo.setId("iddd");
		vo.setName("namess");
		vo.setPasswd("passwdddd");
		
		int result=service.insert(vo);
		return mv;
	}
		
	@GetMapping("/update")
		public ModelAndView update(ModelAndView mv) {
		
		return mv;
		
	}
	
	//@PostMapping("/update")
	@GetMapping("/testUpdate")
	public ModelAndView update(ModelAndView mv, MemberVo vo) {
		vo.setEmail("user3333@s.s");
		vo.setId("user3");
		vo.setPasswd("user333");
		service.update(vo);
		
		return mv;
		
	}
	
	@GetMapping("/delete")
		public ModelAndView delete(ModelAndView mv) {
		
		String id = "iddd";
		service.delete(id);
		
		
		
		
		return mv;
		}
	
	@GetMapping("/info")
		public ModelAndView selectOne(ModelAndView mv  ) {
		
		String id="user3";
		MemberVo result = service.selectOne(id);
		return mv;
		}
	
	@GetMapping("/list")
		public ModelAndView selectList(ModelAndView mv){	
		
		List<MemberVo> result = service.selectList();
		return mv;
		}


	
	
	
	
}
