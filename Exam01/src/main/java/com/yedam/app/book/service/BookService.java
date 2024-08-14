package com.yedam.app.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

public interface BookService {

	//전체조회
	public List<BookVO> bookList();
	
	//등록
	public int bookInsert(BookVO bookVO);
}
