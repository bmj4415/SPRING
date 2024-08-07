package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service // 누락시키지 않도록 주의!
//@AllArgsConstructor // 집에가서 복습해볼것. 

public class EmpServiceImpl implements EmpService {

	private EmpMapper empMapper;

	@Autowired
	EmpServiceImpl(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}

	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		// 사원번호가 어떤 값을 가질지는 모르지만 최소한 음수값을 가지지는 않기 때문에
		// 정상적인 값이라면 새로 등록된 사원번호를, 아니면 -1
		return result == 1 ? empVO.getEmployeeId() : -1;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;

		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);

		if (result == 1) {
			isSuccessed = true;
		}

		// isSuccessed는 boolean타입, empVO EmpVO 타입 => 서로 타입이 다른 값을 다루기에는 Map이 편리함.
		map.put("result", isSuccessed);
		map.put("target", empVO);

		return map;
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		Map<String, Object> map = new HashMap<>();

		int result = empMapper.deleteEmpInfo(empId);

		if (result == 1) {
			map.put("employeeId", empId);
		}
		//{}
		//{"employeeId" : 104} => delete가 정상적으로 진행되었을 경우
		return map;
	}


	
}
