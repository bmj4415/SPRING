package com.yedam.app.common.config;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //모든 컨트롤러에 공통적으로 적용되는 기능 별도 관리
public class WebAdvice {
	
	//@ExceptionHandler : 특정 예외에 대한 처리 등록
	@ExceptionHandler(value=NullPointerException.class)
	public ResponseEntity handleSqlException() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
@ModelAttribute("contextPath") //@ModelAttribute : 모델에 담는 변수를 선언. model.addAttribute와 역할이 거의 동일
public String contextPath(HttpServletRequest req) {
	return req.getContextPath();
}

}
