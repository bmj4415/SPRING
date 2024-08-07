package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
//@Getter
//@Setter
//@RequiredArgsConstructor
//@ToString
//@EqualsAndHashCode

public class EmpVO {
	// employee_id => employeeId(mybatis.configuration.map-underscore-to-camel-case
	// = true 설정이 true라서)
	private Integer employeeId; // null 값을 처리하기 위해 Integer
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd") //이 어노테이션을 쓰는 경우 yyyy/MM/dd를 할 경우 오류가 날 수 있음(둘 중 택1)
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
	private int departmentId;
}
