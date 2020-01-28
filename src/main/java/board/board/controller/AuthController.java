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

/**
 * セキュリティに対するリクエストを処理するcontroller
 * memberのデータの作業をserviceに委任して処理
 * 処理が終わった資料をユーザーが見るviewと一緒に伝達
 * 
 * @author(最後の修正者) Eonsu Bae(2020-01-28)
 * @version 1.0
 */

@Controller
public class AuthController {

	/* ユーザのデータに対する作業を委任されて処理するserviceを注入されたメンバーバリアブル */
	@Autowired
	MemberService memberService;

	/* WebSecurityConfigのクラスで設定いた暗号化ができるオブジェクトを注入されたメンバーバリアブル */
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	/**
	 * 加入のページを持ってくるメソッド
	 * 
	 * @return viewの経路のバリュー 
	 */
	@GetMapping("/signup")
	public String getSignupPage() throws Exception {
		return "auth/signup";
	}
	
	/**
	 * 加入を処理するメソッド
	 * 
	 * @param MemberDto member フォームから貰うmemberの内容を持っているオブジェクト
	 * @return リダイレクトの経路のバリュー 
	 */
	@PostMapping("/signup")
	public String signup(MemberDto member) throws Exception {
		member.setPassword(bcryptPasswordEncoder.encode(member.getPassword()));
		memberService.insertMember(member);

		return "redirect:/login";
	}

	/**
	 * ログインのページを持ってくるメソッド
	 * 
	 * @return viewの経路のバリュー 
	 */
	@GetMapping("/login")
	public String getLoginPage() throws Exception {
		return "auth/login";
	}

	/**
	 * ログインになったユーザの情報を見せるページを持ってくるメソッド
	 * 
	 * @param Principal principal 現在のセッションの情報を持っているオブジェクト
	 * @return 現在のセッションのオブジェクトとviewの経路を持っているModelAndViewのオブジェクト  
	 */
	@GetMapping("/account")
	public ModelAndView loginSuccess(Principal principal) throws Exception {
		ModelAndView mv = new ModelAndView("auth/account");
		MemberDto memberDto = memberService.findById(principal.getName());
		mv.addObject("user", memberDto);

		return mv;
	}

	/**
	 * 権限がないユーザに見せるページを持ってくるメソッド
	 * 
	 * @return viewの経路のバリュー 
	 */
	@GetMapping("/accessDenied")
	public String handleAccessDeny() throws Exception {
		return "auth/err/forbidden";
	}
}
