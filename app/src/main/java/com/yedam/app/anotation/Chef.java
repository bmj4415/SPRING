package com.yedam.app.anotation;

import org.springframework.stereotype.Component;

@Component //등록하고자 하는 클래스 위에 anotation이 붙음 => 빈으로 등록하겠다는 선언
public class Chef {
	public void cooking() {
		System.out.println("Spring 어노테이션 방식");
	}
}
