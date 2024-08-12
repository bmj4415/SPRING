package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller
public class DeptController {
	private DeptService deptService;

	@Autowired
	DeptController(DeptService deptService) {
		this.deptService = deptService;
	}

//전체조회
	@GetMapping("deptList")
	public String deptList(Model model) {
		// 1) 해당 기능 수행하는 서비스
		List<DeptVO> list = deptService.deptList();
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("dept", list);
		// 3) 데이터를 출력할 페이지 결정
		return "dept/list";
	}

//단건조회
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept", findVO);
		return "dept/info";
	}

//등록(페이지)
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert";
	}

//등록(처리기능)
	@PostMapping("deptInsert")
	public String deptInsertProcess(DeptVO deptVO) {
		int deptid = deptService.deptInsert(deptVO);
		String url = null;

		if (deptid > -1) {
			url = "redirect:deptInfo?departmentId=" + deptid;
		} else {
			url = "redirect:deptList";
		}

		return url;
	}

//수정(페이지)
	@GetMapping("deptUpdate")
	public String deptUpdateForm(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept", findVO);
		return "dept/update";
	}

//수정(기능처리)
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object> deptUpdateQueryString(DeptVO deptVO) {
		return deptService.deptUpdate(deptVO);
	}

//삭제(기능처리)
	@GetMapping("deptDelete")
	public String deptDelete(Integer departmentId) {
		deptService.deptDelete(departmentId);
		return "redirect:deptList";

	}

}
