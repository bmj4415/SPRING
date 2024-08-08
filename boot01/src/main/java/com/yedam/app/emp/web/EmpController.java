package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;


@Controller // node에서 쓰는 route(:사용자의 요청(endpoint)에 대한 처리)와 동일한 개념
			// : URL + METHOD => Service => View(화면)
public class EmpController {
	//해당 컨트롤러에서 제공하는 서비스
	private EmpService empService; //서비스의 개수는 상관없지만 갯수만큼 빈 생성 필요
	
	@Autowired
	EmpController(EmpService empService) {
		this.empService = empService;
	}
	
	// GET : 조회, 빈 페이지
	// POST : 데이터 조작(등록, 수정, 삭제)
	
	//전체조회 : GET
	@GetMapping("empList")
	public String empList(Model model) { //Model = Request + Response
		
		// 1) 해당 기능 수행 => Service
		List<EmpVO> list = empService.empList();
		
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list); //key와 실제 데이터
		return "emp/list"; // 3) 데이터를 출력할 페이지 결정
		
		//classpath:/templates/  emp/list .html
		// prefix				 return		suffix
		//return하는 문자열에는 절대 '/'가 붙으면 안됨!
	}
	
	//단건조회 : Get => QueryString을 가장 많이 사용함
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) { //EmpVO empVO : 1건을 가르키는 조건
		
		//1) 해당 기능 수행 => Service
		EmpVO findVO = empService.empInfo(empVO);
		
		//2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emp", findVO);
		return "emp/info"; //3) 데이터를 출력할 페이지 결정
		
		// classpath:/templates/  emp/info  .html
		//     prefix				return    suffix
		
		//return "redirect:empList";
	}
	
	//등록 - 페이지
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	//등록 - 처리 : POST => form 태그를 통한 submit
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		String url = null;
		
		if(eid > -1) {
			//정상적으로 등록된 경우(사원번호를 리턴하기로 되어있음)
			url = "redirect:empInfo?employeeId=" + eid;
		} else {
			//등록되지 않은 경우
			url = "redirect:empList"; //페이지가 아니라 url
		}
		return url;
	}
	
	//수정 - 페이지 : Get(=단건조회)
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO emoVO, Model model) {
		EmpVO findVO = empService.empInfo(emoVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}
	
	
	//수정 - 처리 (AJAX => QueryString으로 값을 받음)
	//@PostMapping("empUpdate")
	@ResponseBody // AJAX 존재하는 위치는 return 위(return하는 내용을 클라이언트에게 돌려주기 위함)
	public Map<String, Object> empupdateAJAXQueryString( EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	
	
	//수정 - 처리 (AJAX => JSON으로 값을 받음)
	@PostMapping("empUpdate")
	@ResponseBody 
	public Map<String, Object> empupdateAJAXJSON(@RequestBody EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	
	
	//삭제 - 처리 : GET 방식
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList"; //ajax 방식을 사용하게 되면 화면에서 삭제된 데이터를 다시 처리해줘야함. 권장하는 방법 x
	}
	
	
}
