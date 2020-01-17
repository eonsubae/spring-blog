package board.board.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.MemberDto;
import board.board.service.MemberService;

@Controller
public class AuthController {

	@Autowired
	MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@GetMapping("/signup")
	public String getSignupPage() throws Exception {
		return "auth/signup";
	}

	@PostMapping("/signup")
	public String signup(MemberDto member) throws Exception {
		member.setPassword(bcryptPasswordEncoder.encode(member.getPassword()));
		memberService.insertMember(member);

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String getLoginPage() throws Exception {
		return "auth/login";
	}

	@GetMapping("/account")
	public ModelAndView loginSuccess(Principal principal) throws Exception {
		ModelAndView mv = new ModelAndView("auth/account");
		MemberDto memberDto = memberService.findById(principal.getName());
		mv.addObject("user", memberDto);

		return mv;
	}

	@GetMapping("/accessDenied")
	public String handleAccessDeny() throws Exception {
		return "auth/err/forbidden";
	}
}
