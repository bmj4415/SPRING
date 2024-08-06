package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	// 전체조회
	public List<EmpVO> selectEmpAllList(); // EmpVO 맛의 사탕들(데이터 타입)만 모아서 주머니(List)에 담고 EmpVO라고 스티커를 붙임

	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);

	// 등록
	public int insertEmpInfo(EmpVO empVO);

	// 수정
	public int updateEmpInfo(@Param("id") int empId, @Param("emp") EmpVO empVO);
	//"id", "emp" : 변수이름(sql에서 참조할 때 사용), empId/empVO : 코드에서 사용하는 변수이름
	
	// 삭제
	public int deleteEmpInfo(int empId);
}
