package board.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.board.dto.BoardDto;

/**
 * boardに対するMybatis mapperのクラス
 * ビジネスロジックはserviceで処理すべきだ
 * 
 * @author Eonsu Bae(2020-01-28) 
 * @version 1.0
 */

@Mapper
public interface BoardMapper {
	int getBoardsCount() throws Exception;
	List<BoardDto> selectBoardList() throws Exception;
	List<BoardDto> selectBoardListByPagination(@Param("skip") int skip, @Param("limit") int limit) throws Exception;
	List<BoardDto> selectBoardLimit(int count) throws Exception;
	BoardDto selectBoardOne(int boardIdx) throws Exception;
	void insertBoard(BoardDto board) throws Exception;
	void updateBoard(BoardDto board) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
	void increaseHitCnt(int boardIdx) throws Exception;
}
