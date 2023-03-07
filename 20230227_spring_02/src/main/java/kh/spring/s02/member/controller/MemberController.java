package kh.spring.s02.member.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.s02.member.model.service.MemberService;
import kh.spring.s02.member.model.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService service;

	@GetMapping("/signUp")
	public ModelAndView viewInsert(ModelAndView mv) {
		mv.setViewName("member/signUp");
		return mv;
	}
	 @PostMapping("/signUp")	
	 public ModelAndView insert(ModelAndView mv, MemberVo vo
			 , String bbb
			 , String id
			 , RedirectAttributes rttr) throws Exception{
		
		int result= -1;
		if(result>0) {
			//회원가입 성공
	    //	1.방법1 -> 사용불가방법
		//	mv.setViewName("redirect:/?msg=회원가입성공");
		//  방법2
		//	mv.addObject("msg", "회원가입성공");
		//	mv.setViewName("error/errorFailure");
			// 방법3 - spring 에서만
			rttr.addFlashAttribute("msg", "회원가입성공");
			mv.setViewName("redirect:/");
			
		}else {
			//회원가입 실패
			//방벙3 
			rttr.addFlashAttribute("msg", "회원가입 실패");
			mv.setViewName("redirect:/member/signUp");
		}
		
		return mv;
	}
		
	@GetMapping("/update")
		public ModelAndView update(ModelAndView mv	
				,@RequestParam("id") String id,
				int aaa
				//id 없이 진입 불가능 반드시 있어야함
				// String id : id 라는 이름의 parameter 없어도 됨. 없는 경우 null값이 들어감
				
				) throws Exception{
		
		MemberVo vo = service.selectOne(id);
		mv.addObject("memberVo", vo);
		mv.setViewName("/member/update");
		return mv;
		
	}
	
	@PostMapping("/update")
	public ModelAndView update(ModelAndView mv, MemberVo vo) throws Exception{
		
		service.update(vo);
		
		return mv;
		
	}
	
	@GetMapping("/delete")
		public ModelAndView delete(ModelAndView mv
				, String id
				) throws Exception{
		
		service.delete(id);
		return mv;
		}
	
	@GetMapping("/info")
		public ModelAndView selectOne(ModelAndView mv
				, String id //request.getParameter("") 
				) throws Exception{
		
		if(id == null) {
			mv.setViewName("redirect:/");
			return mv;
		}
		
		MemberVo result = service.selectOne(id);
		mv.addObject("membervo", result);
		mv.setViewName("member/info");
		return mv;
		}
	
	@GetMapping("/list")
		public ModelAndView selectList(ModelAndView mv) throws Exception{	
		
		List<MemberVo> result = service.selectList();
		mv.addObject("membervolist", result);
		return mv;
		}

	//프로젝트 중 후반에 작성하기 - 그전에는 각각 try-catch
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView memberNullPointExceptionHandler(NullPointerException e
			// 오류발생함. ModelAndView mv
			) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg",e.getMessage()+ "오류발생 다시시도하세요");
		mv.setViewName("redirect:/error500");
		return mv;
	}

	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView memberNumberFormatExceptionHandler(NumberFormatException e
			// 오류발생함. ModelAndView mv
			) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg",e.getMessage()+ "오류발생 다시시도하세요");
		mv.setViewName("redirect:/error500");
		return mv;
	}
	@ExceptionHandler(SQLException.class)
	public ModelAndView memberSQLExceptionHandler(SQLException e
			// 오류발생함. ModelAndView mv
			) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg",e.getMessage()+ "오류발생 다시시도하세요");
		mv.setViewName("redirect:/error500");
		return mv;
	}

	@ExceptionHandler
	public ModelAndView memberExceptionHandler(Exception e
			// 오류발생함. ModelAndView mv
			) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg",e.getMessage()+ "오류발생 다시시도하세요");
		mv.setViewName("redirect:/error500");
		return mv;
	}
	
	
	
}
