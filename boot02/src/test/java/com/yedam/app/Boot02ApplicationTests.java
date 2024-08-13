package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;

@SpringBootTest
class Boot02ApplicationTests {

	@Autowired
	BoardMapper boardMapper;
	
	//전체조회 test
	  @Test
	  void selectBoardAll() {
		  
	  }

}
