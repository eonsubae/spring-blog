package board.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import board.board.dto.MemberDto;
import board.board.mapper.MemberMapper;

@Service
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;

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
