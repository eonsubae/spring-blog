package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

@Controller
public class MainController {

	@Autowired
	BoardService boardService;

	@RequestMapping("/")
	public ModelAndView getMainPage() throws Exception {
		ModelAndView mv = new ModelAndView("main");
		List<BoardDto> recentThreeContent = boardService.selectBoardLimit(6);
		mv.addObject("boards", recentThreeContent);

		return mv;
	}
}
