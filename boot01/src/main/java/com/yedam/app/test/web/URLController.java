package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Bean 등록, Web과 관련된 부분
public class URLController {
	
	//전통적인 방식
	//@RequestMapping(path="/test", method=RequestMethod.GET)
	
	//권장방식
	@GetMapping("/test")
	
	@ResponseBody
	public String urlGetTest(String keyword) { //경로, 메소드
		return "Server Response : Get Method\n Select - " + keyword;
	}

}
