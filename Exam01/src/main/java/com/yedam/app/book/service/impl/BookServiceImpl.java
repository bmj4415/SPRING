package com.yedam.app.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.book.mapper.BookMapper;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Service
public class BookServiceImpl implements BookService {

	private BookMapper bookMapper;
	
	@Autowired
	public BookServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

	@Override
	public List<BookVO> bookList() {
		return bookMapper.getBookList();
	}

	@Override
	public int bookInsert(BookVO bookVO) {
		int result = bookMapper.insertBookInfo(bookVO);
		return result == 1 ? bookVO.getBookNo() : -1;
	}
	
	
}
