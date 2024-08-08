package com.yedam.app.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //1) 모든 컨트롤러에 대해 전역적으로 적용 2) 예외처리
public class WebControllerAdvice {
@ModelAttribute("contextPath")
public String contextPath(final HttpServletRequest request) { //final : request변수가 다른 값으로 변경될 수 없도록 함.
	return request.getContextPath();
}
}
