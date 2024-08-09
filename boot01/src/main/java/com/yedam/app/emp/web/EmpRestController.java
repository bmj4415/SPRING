package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;


//코드 확인 작업시 부메랑 필요함!


@RestController // @controller + 모든 메소드에 암묵적으로 @ResponseBody를 적용했다는 선언
				// @ResponseBody : AJAX

public class EmpRestController {
	private EmpService empService;

	@Autowired
	EmpRestController(EmpService empService) {
		this.empService = empService;
	}
	// RestController의 경우 페이지가 필요없기 때문에 컨트롤러가 하나씩이면 됨.

	// 전체조회 : GET => emps //http://localhost:8099/yedam/emps(페이지로 확인)
	@GetMapping("emps")
	public List<EmpVO> empList() {
		return empService.empList();
	}

	// 단건조회 : GET => emps/100(확인 : http://localhost:8099/yedam/emps/100) - 부메랑
	@GetMapping("emps/{eid}")
	public EmpVO empInfo(@PathVariable(name = "eid") Integer employeeId) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		return empService.empInfo(empVO);
	}

	// 등록 : POST => emps (부메랑으로 확인 후 결과에 나오는 번호 : PrimaryKey)
	@PostMapping("emps") // @RequestBody : JSON으로 데이터를 받는다
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}

	// 수정 : PUT => emps/100
	@PutMapping("emps/{employeeId}") // 다른 방법도 있지만 가장 많이 쓰이는 방법(경로에 수정하고자 하는 대상을 받아옴)
	public Map<String, Object> empUpdate(@PathVariable Integer employeeId,// 경로를 통해서 수정할 Target(대상)을 받아오기 위해 사용(like:프라이머key) 
										 @RequestBody EmpVO empVO) {// 수정할 정보는 JSON 포맷으로
		empVO.setEmployeeId(employeeId); //@PathVariable과 @RequestBody 방법 2가지로 나눠 받았기 때문에 하나의 VO로 합쳐주는 과정이 필요함.
		return empService.empUpdate(empVO);
	}

	// 삭제 : DELETE => emps/100
	@DeleteMapping("emps/{employeeId}")
	public Map<String, Object> empDelete(@PathVariable Integer employeeId) {
		return empService.empDelete(employeeId);
	}
}
