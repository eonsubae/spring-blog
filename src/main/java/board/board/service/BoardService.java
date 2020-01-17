package board.board.service;

import java.util.List;

import board.board.dto.BoardDto;

public interface BoardService {
	int getBoardsCount() throws Exception;
	List<BoardDto> selectBoardList() throws Exception;
	List<BoardDto> selectBoardListByPagination(int skip, int limit) throws Exception;
	List<BoardDto> selectBoardLimit(int count) throws Exception;
	BoardDto selectBoardOne(int boardIdx) throws Exception;
	void insertBoard(BoardDto board) throws Exception;
	void updateBoard(BoardDto board) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
}
