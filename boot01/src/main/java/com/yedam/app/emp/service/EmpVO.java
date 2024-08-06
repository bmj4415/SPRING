package com.yedam.app.emp.service;

import java.util.Date;

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
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
	private int departmentId;
}
