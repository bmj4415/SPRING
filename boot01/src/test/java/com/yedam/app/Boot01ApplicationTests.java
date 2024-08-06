package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest // test 환경에서 IoC 컨테이너를 생성
class Boot01ApplicationTests {

	@Autowired //필드주입(보안문제가 있어 실제 사용x test 환경에서만)
	EmpMapper empMapper;
	//Spring은 EmpMapper 인터페이스의 구현객체를 찾아서 empMapper 변수에 자동으로 주입
	
	//전체조회 test
	@Test
	void empList() {
	List<EmpVO> list = empMapper.selectEmpAllList();
	assertTrue(!list.isEmpty()); // 값이 비었는지 아닌지를 체크
	}
	
	//단건조회 test
	//@Test
	void empInfo() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals("King", findVO.getLastName());
	}
	
	//등록 test
	//@Test
	void empInsert() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hong");
		empVO.setEmail("kdHong123@google.com");
		empVO.setJobId("IT_PROG");
		empVO.setSalary(1000);
		
		int result = empMapper.insertEmpInfo(empVO);
		System.out.println(empVO.getEmployeeId());
		
		assertEquals(1, result);
	}
	
	//수정
	//@Test
	public void empUpdate() {
		
		//1) 수정 작업 전 기존 데이터 단건조회
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(4322);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		System.out.println(findVO);
		
		//2) 수정할 내용 입력
		findVO.setLastName("Kang");
		
		//3) Update 작업
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		assertEquals(1, result);
		
	}
	
	
	
	//삭제 test (*단위 test 실행 순서가 보장되지 않으므로 입력/삭제에서 순서 오류가 발생할 수 있음)
	//@Test
	public void empDelete() {
		int result = empMapper.deleteEmpInfo(4322);
		assertEquals(1, result);
	}
	
}
