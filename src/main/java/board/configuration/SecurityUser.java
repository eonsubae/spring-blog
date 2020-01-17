package board.configuration;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import board.board.dto.MemberDto;

public class SecurityUser extends User {
    private static final long serialVersionUID = 1L;
    
    public SecurityUser(MemberDto memberDto) {
    	super(memberDto.getId(), memberDto.getPassword(), 
    		AuthorityUtils.createAuthorityList(memberDto.getRole().toString()));
    	
    }
}
