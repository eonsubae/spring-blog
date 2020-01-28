package board.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import board.board.dto.MemberDto;

/**
 * memberに対するMybatis mapperのクラス
 * ビジネスロジックはserviceで処理すべきだ
 * 
 * @author Eonsu Bae(2020-01-28) 
 * @version 1.0
 */

@Mapper
public interface MemberMapper {
    MemberDto findById(String username) throws Exception;
	void insertMember(MemberDto member);
}
