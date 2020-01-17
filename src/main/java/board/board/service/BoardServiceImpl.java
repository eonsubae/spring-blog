package board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public int getBoardsCount() throws Exception {
		return boardMapper.getBoardsCount();
	}

	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public List<BoardDto> selectBoardListByPagination(int skip, int limit) throws Exception {
		return boardMapper.selectBoardListByPagination(skip, limit);
	}

	@Override
	public List<BoardDto> selectBoardLimit(int count) throws Exception {
		return boardMapper.selectBoardLimit(count);
	}

	@Override
	public BoardDto selectBoardOne(int boardIdx) throws Exception {
		boardMapper.increaseHitCnt(boardIdx);
		return boardMapper.selectBoardOne(boardIdx);
	}

	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board);
	}

	@Override
	public void updateBoard(BoardDto board) throws Exception {
        boardMapper.updateBoard(board);		
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
        boardMapper.deleteBoard(boardIdx);		
	}
	
}
