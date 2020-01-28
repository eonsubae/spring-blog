package board.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.board.dto.MemberDto;
import board.board.mapper.MemberMapper;

/**
 * boardに関するビジネスロジックとトランザクションを作成するクラス
 * 
 * @author Eonsu Bae(2020-01-28) 
 * @version 1.0
 */

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper memberMapper;

	@Override
	public MemberDto findById(String username) throws Exception {
		MemberDto memberDto = memberMapper.findById(username);
		return memberDto;
	}

	@Override
	public void insertMember(MemberDto member) throws Exception {
        memberMapper.insertMember(member);
	}

}
