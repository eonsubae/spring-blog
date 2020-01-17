package board.board.service;

import board.board.dto.MemberDto;

public interface MemberService {
	MemberDto findById(String username) throws Exception;
    void insertMember(MemberDto member) throws Exception;
}
