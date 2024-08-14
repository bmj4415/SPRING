package com.yedam.app.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookVO {
	private Integer bookNo;//책번호
	private String bookName;//책이름
	private String bookCover;//책표지
	@DateTimeFormat(pattern= "yyyy/MM/dd")
	private Date bookDate; //발행일
	private int bookPrice; //가격
	private String bookPublisher; //출판사
	private String bookInfo; //책정보
}
