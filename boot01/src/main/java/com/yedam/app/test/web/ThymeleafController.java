package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ThymeleafController {
private EmpService empService;

@GetMapping("thymeleaf")
public String thymeleafTest(Model model) {
	EmpVO empVO = new EmpVO();
	empVO.setEmployeeId(100);
	
	EmpVO findVO = empService.empInfo(empVO);
	model.addAttribute("empInfo", findVO);
	//model.addAttribute => 클라이언트가 아니라 request에 일시적으로 담았다가 페이지에 전달
	
	return "test";
}
}
