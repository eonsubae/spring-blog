package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

/**
 * 初めのページに対するリクエストを処理するcontroller
 * 
 * @author(最後の修正者) Eonsu Bae(2020-01-28)
 * @version 1.0
 */

@Controller
public class MainController {

	/* boardのデータに対する作業を委任されて処理するserviceを注入されたメンバーバリアブル */
	@Autowired
	BoardService boardService;
	
	/* 初めのページに見せる最近のコンテンツの数字のメンバーバリアブル */
	private int mainContentCount = 6;

	/**
	 * 初めのページを持ってくるメソッド
	 * 
	 * @return 最近のコンテンツのオブジェクトとviewの経路を持っているModelAndViewのオブジェクト */
	@RequestMapping("/")
	public ModelAndView getMainPage() throws Exception {
		ModelAndView mv = new ModelAndView("main");
		List<BoardDto> recentContent = boardService.selectBoardLimit(mainContentCount);
		mv.addObject("boards", recentContent);

		return mv;
	}
}
