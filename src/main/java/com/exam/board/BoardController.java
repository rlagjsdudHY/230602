package com.exam.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.board.dao.BoardDao;
import com.exam.board.dto.BoardDto;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	// 메인페이지 리퀘스트 맵핑
	@RequestMapping("/")
	public String root() throws Exception{
		return "redirect:boardList";
	}
	
	//목록페이지 리퀘스트 맵핑
	@RequestMapping("/boardList")
	public String boardList(Model model) {
		model.addAttribute("mtdBoardList", boardDao.boardListDao());
		return "boardList";
	}

	//내용보기 리퀘스트 맵핑
	@RequestMapping("/boardView")
	public String boardView(HttpServletRequest req, Model model) {
		try { 
				req.setCharacterEncoding("UTF-8");
				int num = Integer.parseInt(req.getParameter("num"));
				model.addAttribute("mtdBoardView", boardDao.boardViewDao(num));
		} catch (Exception e) {
				e.getMessage();
		}
		return "boardView";
	}

	//글입력양식 리퀘스트 맵핑
	@RequestMapping("/writeForm")
	public String writeForm() {
		return	"writeForm";
	}

	//글저장 리퀘스트 맵핑
	@RequestMapping("/boardWrite")
	public String boardWrite(HttpServletRequest req,Model model) {
			try {
				req.setCharacterEncoding("UTF-8");
				String writer = req.getParameter("writer");
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				boardDao.boardWriteDao(writer, title, content);
				
			} catch (Exception e) {
					e.getMessage();
			}
		
		return "redirect:boardList";
	}
	//글삭제 리퀘스트 맵핑
	@RequestMapping("/boardDelete")
	public String boardDelete(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
			int num = Integer.parseInt(req.getParameter("num"));
			boardDao.boardDeleteDao(num);
		} catch (Exception e) {
			e.getMessage();
		}
		
		return "redirect:boardList";
	}
	
	//글검색 
	@RequestMapping("/search")
	public String boardSearch(@RequestParam("keyword") String keyword, Model model) {
		 List<BoardDto> mtdBoardList = boardDao.boardSearch(keyword);
		try {
			
			model.addAttribute("mtdBoardList", mtdBoardList);
		} catch (Exception e) {
			e.getMessage();
		}	
		return "boardList";
	}
	 
	

	
}
