package board.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import board.board.dto.MemberDto;

@Mapper
public interface MemberMapper {
    MemberDto findById(String username) throws Exception;
	void insertMember(MemberDto member);
}
