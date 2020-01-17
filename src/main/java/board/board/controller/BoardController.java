package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	private static final int pageSize = 10;

	@GetMapping("/boards")
	public ModelAndView getBoards(
		@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum
	) throws Exception {
		int totalBoardsCount = boardService.getBoardsCount();
		int totalPageNum = totalBoardsCount / pageSize + 1;
		int skip = pageSize * (pageNum - 1);
		
		ModelAndView mv = new ModelAndView("board/boardList");

		List<BoardDto> list = boardService.selectBoardListByPagination(skip, pageSize);
		mv.addObject("list", list);
		mv.addObject("pageNum", pageNum);
		mv.addObject("totalPageNum", totalPageNum);

		return mv;
	}
	
	@GetMapping("/boards/{boardIdx}")
	public ModelAndView getBoard(@PathVariable int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("board/boardDetail");
		
		BoardDto board = boardService.selectBoardOne(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
	@GetMapping("/boards/editor")
	public String getEditor() throws Exception {
		return "/board/boardWrite";
	}
	
	@GetMapping("/boards/editor/{boardIdx}")
	public ModelAndView getEditorForUpdate(@PathVariable int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("board/boardUpdate");
		
		BoardDto board = boardService.selectBoardOne(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
	@PostMapping("/boards")
	public String insertBoard(BoardDto board) throws Exception {
		boardService.insertBoard(board);
		return "redirect:/boards";
	}
	
	@PostMapping("/boards/editor/{boardIdx}")
	public String updateBoard(BoardDto board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/boards";
	}
	
	@PostMapping("/boards/remover/{boardIdx}")
	public String deleteBoard(@PathVariable int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/boards";
	}
}
