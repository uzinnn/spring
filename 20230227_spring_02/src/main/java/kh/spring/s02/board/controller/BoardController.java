package kh.spring.s02.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kh.spring.s02.board.model.service.BoardService;
import kh.spring.s02.board.model.vo.BoardVo;
import kh.spring.s02.common.file.FileUtil;



@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;

	@Autowired
	@Qualifier("fileUtil")
	private FileUtil fileUtil;
	
	private final static int BOARD_LIMIT = 5; 
	private final static int PAGE_LIMIT = 3;
	private final static String UPLOAD_FOLDER = "\\resources\\uploadfiles";
	
	//검색단어는 제목,내영,단어,작성자에게 포함되어있으면 찾기
			//null 또는 ""는 검색하지않음
			//String searchWord="";
			//String searchWord=null;
			
			String searchWord="user";
			
	@RequestMapping("/list")
	//@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard( ModelAndView mv, HttpServletRequest req) {
		// 寃��깋�떒�뼱�뒗 �젣紐�,�궡�슜,�옉�꽦�옄�뿉�꽌 �룷�븿�릺�뼱�엳�쑝硫� 李얘린
				// null �삉�뒗 "" �� 寃��깋�븯吏� �븡�쓬.
//				String searchWord = null;  
//				String searchWord = "";  
				String searchWord = "�떟";

				try {
					req.setCharacterEncoding("UTF-8");
					searchWord = req.getParameter("searchWord");
					System.out.println("�븳湲� �솗�씤: "+ searchWord);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				// TODO
				int currentPage = 1;
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
		String boardTitle = "�닔�젙�젣紐�";
		String boardContent = "�닔�젙�궡�슜";
		String boardOriginalFilename = "";  // "" �뙆�씪�뾾�쓬
		String boardRenameFilename = "";  // "" �뙆�씪�뾾�쓬
		
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
	
	//URL
	//1. /board/read?boardNum=26&replyPage=3
	//2. /board/read/27/3
	
	//글 상세읽기화면
	@GetMapping("/read/boardNum/{replyPage}")
	public ModelAndView viewReadBoard(
			ModelAndView mv,
			
			@PathVariable int boardNum,
			@PathVariable int replyPage
			//@RequestParam("boardNum") int boardNum
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
	
	//원글작성
	@PostMapping("/insert")
	public ModelAndView doInsertBoard(ModelAndView mv
			, MultipartHttpServletRequest multiReq
			, MultipartFile report //==@RequestParam(name="report", required=false) MultipartFile multi
			, HttpServletRequest request
			, BoardVo vo
			
			) {
	
		Map<String,String> filePath;
		List<Map<String,String>> fileListPath;
		try {
			//fileListPath =fileUtil.saveFileList(multiReq, request, null);
			filePath = fileUtil.saveFile(report, request, null);
			//vo.setBoardOriginalFilename(report.getOriginalFilename());
			//vo.setBoardRenameFilename(renameFilePath); //resources/fileupload/uuid_a.png
			vo.setBoardOriginalFilename(filePath.get("original"));
			vo.setBoardRenameFilename(filePath.get("rename"));
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		vo.setBoardWriter("user22");//TODO
		int result = service.insert(vo);
		return mv;
	}
	
	@PostMapping("/insertReplyAjax")
	@ResponseBody
	public String inser(BoardVo vo, MultipartFile report) {
		if( report != null) {
			System.out.println(report.getOriginalFilename());
		}else {
			System.out.println("ㅠㅏ일업ㅇㄹ");
		}
    	System.out.println("######");
		System.out.println(vo);
//		int boardNum = 6;
//		vo.setBoardNum(boardNum);
//		
//		vo.setBoardContent("임시 6답내용");
//		vo.setBoardTitle("임시6답제목");
		vo.setBoardWriter("user22");
		
		// 답글작성
		service.insert(vo);
		List<BoardVo> replyList = service.selectReplyList(vo.getBoardNum());
		//ajax는 mv에 실어갈 ㅅ우 없음 mv.addObject("replyList",replyList);
		
		return new Gson().toJson(replyList);
		//�옄諛뷀삎�쓣 json �삎�깭濡�(object) 諛붽퓭�꽌 js�뿉 �꽔�뼱以��떎
	}
	
	
	//답글작성 페이지 이동
	@GetMapping("/insertReply")
	public ModelAndView viewInsertReply(ModelAndView mv
			, int boardNum // 몇번글에 답글인지 
			) {
		mv.setViewName("insertReply");
		return mv;
	}
	// �떟湲��옉�꽦
//	TODO
//	@PostMapping("/insertReply")
	@GetMapping("/insertReplyPostTest")
	public ModelAndView viewInsertReply(ModelAndView mv
			, BoardVo vo
		) {
		// TODO
		int boardNum = 6;
		vo.setBoardNum(boardNum);
		
		vo.setBoardContent("�엫�떆6�떟�궡�슜");
		vo.setBoardTitle("�엫�떆6�떟�젣紐�");
		vo.setBoardWriter("user22");
		
		service.insert(vo);
		
		return mv;
	}
	
//	@RequestMapping(value = "/boardinsert")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {

		return mv;
	}
	
	//@ExceptionHandler
	
}