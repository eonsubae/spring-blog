package board.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import board.board.dto.MemberDto;
import board.board.mapper.MemberMapper;

/*
 * データベースからユーザの情報を持ってくるクラス
 * 
 * @author Eonsu Bae(2020-01-28) 
 * @version 1.0 
 */
@Service
public class BoardUserDetailsService implements UserDetailsService {

	/* データベースの作業を処理するMybatis mapperを注入されたメンバーバリアブル */
	@Autowired
	private MemberMapper memberMapper;

	/**
	 * ユーザを持ってくるメソッド
	 * 
	 * @param String username このバリアブルを使って存在するユーザかどうかをチェックして持ってくる
	 * @return Spring securityのユーザをカスタマイズしたオブジェクト 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    MemberDto memberDto = null;
		try {
			memberDto = memberMapper.findById(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new SecurityUser(memberDto);
	}
	
}
