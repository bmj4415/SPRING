package com.yedam.app.javabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 등록되어 있는 모든 메소드를 순차적으로 모두 실행. 그 결과를 받아 bean으로 등록. 내부에 bean과 같은 설정을 가지고 있음.
public class javaBeanConfig {
	@Bean //메소드를 실행한 결과를 bean으로 등록하겠다(서로 특별한 관계가 없는 경우 빈 등록 방식으로 활용가능)
	public Chef chef() {
		return new Chef();
	}
	
	@Bean //매개변수로 chef를 넘겨서 Restaurant 클래스를 생성
	public Restaurant restaurant(Chef chef) {
		return new Restaurant(chef);
	}
}
