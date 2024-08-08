package com.yedam.app.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //모든 컨트롤러에게 모두 적용하고 싶은 내용이 있을 때 사용
public class WebControllerAdvice {
@ModelAttribute("contextPath")
public String contextPath(final HttpServletRequest request) {
	return request.getContextPath();
}
}
