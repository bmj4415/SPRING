package com.yedam.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
class Boot02ApplicationTests {

	@Autowired
	BoardMapper boardMapper;
	
	//전체조회 test
	  @Test
	  void boardList() {
		  List<BoardVO> list = boardMapper.selectBoardAll();
		  assertTrue(!list.isEmpty());
	  }
	  
	  @Test
	  void boardInfo() {
		  BoardVO boardVO = new BoardVO();
		  boardVO.setBno(1);
		  
		  BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
		  assertEquals("ee",findVO.getTitle());
	  }

}
