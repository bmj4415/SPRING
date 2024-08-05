package com.yedam.app.anotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {

	// Restaurant 클래스는 Chef 클래스에 의존적임.

	private Chef chef;

	// Restaurant 클래스가 이미 가지고 있는 생성자를 주입하는 방법(ref:레퍼런스, 즉 참조)
	// 생성자 인젝션

	@Autowired // 생성자 인젝션 사용 => 쓰고자 하는 생성자 위에 Autowired
	Restaurant(Chef chef) {
		this.chef = chef;
		System.out.println("생성자 인젝션");
	}

	//
	// 세터 인젝션
	Restaurant() {
		System.out.println("세터 인젝션");
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void run() {
		chef.cooking();
	}
}
