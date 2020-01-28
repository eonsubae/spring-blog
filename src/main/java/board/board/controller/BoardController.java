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

/**
 * boardに対するリクエストを処理するcontroller
 * boardのデータの作業をserviceに委任して処理
 * 処理が終わった資料をユーザーが見るviewと一緒に伝達
 * 
 * @author(最後の修正者) Eonsu Bae(2020-01-28)
 * @version 1.0
 */

@Controller
public class BoardController {

	/* boardのデータに対する作業を委任されて処理するserviceを注入されたメンバーバリアブル */
	@Autowired
	private BoardService boardService;
	
	/* boardのリストで何個を見せるのかを決定するメンバーバリアブル */
	private static final int pageSize = 10;

	/** 
	 * boardのリストを持ってくるメソッド 
	 * 
	 * @param int pageNum 現在のboardのリストのページ
	 * @return BoardDtoのオブジェクトのリストとviewの経路を持っているModelAndViewのオブジェクト
	 */
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
	
	/**
	 * 一個のboardのオブジェクトを持ってくるメソッド 
	 * 
	 * @param int boardIdx ユーザが選んだboardのプライマリーキー 
	 * @return 一個のBoardDtoのオブジェクトとviewの経路を持っているModelAndViewのオブジェクト
	 */
	@GetMapping("/boards/{boardIdx}")
	public ModelAndView getBoard(@PathVariable int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("board/boardDetail");
		
		BoardDto board = boardService.selectBoardOne(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
	/**
	 * boardを作成するエディターのページを持ってくるメソッド
	 * 
	 * @return viewの経路のバリュー 
	 */
	@GetMapping("/boards/editor")
	public String getEditor() throws Exception {
		return "/board/boardWrite";
	}
	
	/**
	 * ユーザが選んだboardを修正するページを持ってくるメソッド 
	 * 
	 * @param int boardIdx ユーザが選んだboardのプライマリーキー 
	 * @return 一個のBoardDtoのオブジェクトとviewの経路を持っているModelAndViewのオブジェクト 
	 */
	@GetMapping("/boards/editor/{boardIdx}")
	public ModelAndView getEditorForUpdate(@PathVariable int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("board/boardUpdate");
		
		BoardDto board = boardService.selectBoardOne(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
	/**
	 * 新しいboardを作るメソッド
	 * 
	 * @param BoardDto board フォームから貰うboardの内容を持っているオブジェクト 
	 * @return リダイレクトの経路のバリュー  
	 */
	@PostMapping("/boards")
	public String insertBoard(BoardDto board) throws Exception {
		boardService.insertBoard(board);
		return "redirect:/boards";
	}
	
	/**
	 * 既存のboardを修正するメソッド 
	 * 
	 * @param BoardDto board フォームから貰うboardの内容を持っているオブジェクト
	 * @return リダイレクトの経路のバリュー
	 */
	@PostMapping("/boards/editor/{boardIdx}")
	public String updateBoard(BoardDto board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/boards";
	}
	
	/**
	 * 既存のboardを削除するメソッド
	 * 
	 * @param int boardIdx ユーザが選んだboardのプライマリーキー
	 * @return リダイレクトの経路のバリュー */
	@PostMapping("/boards/remover/{boardIdx}")
	public String deleteBoard(@PathVariable int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/boards";
	}
}
