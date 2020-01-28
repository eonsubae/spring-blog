package board.board.service;

import board.board.dto.MemberDto;

/**
 * memberªÎserviceªòê«úşªòïÒëù
 * 
 * @author Eonsu Bae(2020-01-28) 
 * @version 1.0
 */

public interface MemberService {
	MemberDto findById(String username) throws Exception;
    void insertMember(MemberDto member) throws Exception;
}
