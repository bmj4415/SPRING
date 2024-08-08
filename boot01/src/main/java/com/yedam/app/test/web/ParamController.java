package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@CrossOrigin(origins = "*") //해당 컨트롤러의 모든 요청이 허용되도록 하는 어노테이션(부메랑에서 정상적으로 동작되면 굳이..)
@Controller
public class ParamController {
	//QueryString(질의문자열) : key=value&key=value&...
	//Content-type : application/x-www-form-urlencode
	// Method : 상관없음

	// QueryString => 커맨드 객체(어노테이션 없음) : 객체
	@RequestMapping(path="comobj", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result +="\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	// QueryString => @RequestParam : 기본타입, 단일값처리
	
	@RequestMapping(path="reqparm", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam(@RequestParam Integer employeeId, String lastName, //필수(Integer employeeId) //생략가능(String lastName) => @RequestParam의 여부에 따라 다름
							   @RequestParam(name="msg", defaultValue="No message", required=true) String msg) { //name => 사용자 요청사항에서 name에 지정된 값을 찾음. required=true => 필수 요청, 만약 사용자가 필수요소 부분을 요청하지 않으면 예외를 발생시키지만 defaultValue 값을 지정해놓았기 때문에 예외를 발생시키지 않고 기본값을 출력함.
		String result ="";
		result += "path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}
	
	//@PathVariable
	//: 경로에 값을 포함하는 방식, 단일 값(Method, Content-type 상관없음)
	// 경로로 인식하기 때문에 값이 필수임. 값이 누락되면 경로를 찾을 수 없기 때문에 404 Error 발생
	
	@RequestMapping("path/{id}") //path/Hong, path/1234
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	
	//@RequestBody
	// : JSON 포맷, 배열 or 객체
	// Method : POST, PUT(json은 경로에 포함될 수 없는 문자열이라 기본적으로 body가 필요하기 때문에) // Content-type: application/json
	
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	//json 포맷을 이용하여 배열을 받는 경우
	
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		String result = "path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		return result;
	}
	
	
	
	
}
