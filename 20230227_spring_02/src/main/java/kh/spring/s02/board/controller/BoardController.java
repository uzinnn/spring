package kh.spring.s02.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kh.spring.s02.board.model.service.BoardService;
import kh.spring.s02.board.model.vo.BoardVo;


@Controller
//@WebServlet("/boardinsert")
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	private final static int BOARD_LIMIT = 5; 
	private final static int PAGE_LIMIT = 3;
	
	//검색단어는 제목,내용,작성자에서 포함되어있으면 찾기
			//null 또는 ""은 검색하지 않음
			//String searchWord="";
			//String searchWord=null;
			
			String searchWord="user";
			
	@RequestMapping("/list")
	//@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard( ModelAndView mv, HttpServletRequest req) {
		// 검색단어는 제목,내용,작성자에서 포함되어있으면 찾기
				// null 또는 "" 은 검색하지 않음.
//				String searchWord = null;  
//				String searchWord = "";  
				String searchWord = "답";

				try {
					req.setCharacterEncoding("UTF-8");
					searchWord = req.getParameter("searchWord");
					System.out.println("한글 확인: "+ searchWord);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				// TODO
				int currentPage = 2;
				int totalCnt = service.selectOneCount(searchWord);
				int totalPage = (totalCnt%BOARD_LIMIT==0)?
						(totalCnt/BOARD_LIMIT) : 
						(totalCnt/BOARD_LIMIT) + 1;
				int startPage = (currentPage%PAGE_LIMIT==0) ?
						(currentPage/PAGE_LIMIT -1)*PAGE_LIMIT + 1 :
						(currentPage/PAGE_LIMIT   )*PAGE_LIMIT + 1;
				int endPage = (startPage + PAGE_LIMIT > totalPage) ?
						totalPage : 
						(startPage + PAGE_LIMIT);
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("totalPage", totalPage);
				map.put("startPage", startPage);
				map.put("endPage", endPage);
				map.put("currentPage", currentPage);
				mv.addObject("pageInfo", map);
				
//				mv.addObject("totalPage", totalPage);
//				mv.addObject("startPage", startPage);
//				mv.addObject("endPage", endPage);
//				mv.addObject("currentPage", currentPage);
				
				mv.addObject("boardlist", service.selectList(currentPage, BOARD_LIMIT, searchWord));
				mv.setViewName("board/list");
				return mv;
			}
	
	
	@GetMapping("/update")
	public void viewUpdateBoard() {
		
	}
//	@PostMapping("/update")
	@GetMapping("/updatePostTest")
	public void updateBoard() {
		// TODO
		int boardNum = 1;
		String boardTitle = "수정제목";
		String boardContent = "수정내용";
		String boardOriginalFilename = "";  // "" 파일없음
		String boardRenameFilename = "";  // "" 파일없음
		
		BoardVo vo = new BoardVo();
		vo.setBoardTitle(boardTitle);
		vo.setBoardContent(boardContent);
		vo.setBoardOriginalFilename(boardOriginalFilename);
		vo.setBoardRenameFilename(boardRenameFilename);
		int result = service.update(vo);
		
	}
	
	
	@GetMapping("/delete")
	public void viewDeleteBoard() {
		//TODO
		int boardNum = 10;
		int result = service.delete(boardNum);
	}
	
	//글 상세 읽기 화면
	@GetMapping("/read")
	public ModelAndView viewReadBoard(
			ModelAndView mv,
			@RequestParam("boardNum") int boardNum
			) {
		//TODO
		String writer = "user22";
		
		BoardVo vo = service.selectOne(boardNum, writer);
		mv.addObject("board", vo);
		
		
		List<BoardVo> replyList = service.selectReplyList(boardNum);
		mv.addObject("replyList",replyList);
		mv.setViewName("board/read");
		return mv;
	}
	
	// 원글 작성페이지 이동
	@GetMapping("/insert")
	public ModelAndView viewInsertBoard(
			ModelAndView mv
			, HttpServletRequest req
			, HttpSession session
			, BoardVo vo
			) {
		mv.setViewName("board/insert");
		return mv;
	}
	
	// 원글 작성 
//	@PostMapping("/insert")
	// TODO
	@GetMapping("/insertPostTest")
	public ModelAndView doInsertBoard(ModelAndView mv
			, BoardVo vo
			) {
		vo.setBoardContent("임시내용");
		vo.setBoardTitle("임시제목");
		vo.setBoardWriter("user22");
		int result = service.insert(vo);
		return mv;
	}
	
	@PostMapping("/insertReplyAjax")
	@ResponseBody
	public String inser(BoardVo vo) {
    	System.out.println("######");
		System.out.println(vo);
//		int boardNum = 6;
//		vo.setBoardNum(boardNum);
//		
//		vo.setBoardContent("임시6답내용");
//		vo.setBoardTitle("임시6답제목");
		vo.setBoardWriter("user22");
		
		service.insert(vo);
		List<BoardVo> replyList = service.selectReplyList(vo.getBoardNum());
		//ajax는 mv에 실려갈수없음. mv.addObject("replyList",replyList);
		
		return new Gson().toJson(replyList);
		//자바형을 json 형태로(object) 바꿔서 js에 넣어준다
	}
	
	
	// 답글작성 페이지이동
	@GetMapping("/insertReply")
	public ModelAndView viewInsertReply(ModelAndView mv
			, int boardNum // 몇번글에 답글인지 
			) {
		mv.setViewName("insertReply");
		return mv;
	}
	// 답글작성
//	TODO
//	@PostMapping("/insertReply")
	@GetMapping("/insertReplyPostTest")
	public ModelAndView viewInsertReply(ModelAndView mv
			, BoardVo vo
		) {
		// TODO
		int boardNum = 6;
		vo.setBoardNum(boardNum);
		
		vo.setBoardContent("임시6답내용");
		vo.setBoardTitle("임시6답제목");
		vo.setBoardWriter("user22");
		
		service.insert(vo);
		
		return mv;
	}
	
//	@RequestMapping(value = "/boardinsert")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {

		return mv;
	}
	
}