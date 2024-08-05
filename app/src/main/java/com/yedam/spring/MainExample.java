package com.yedam.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {
public static void main(String[] args) {
	ApplicationContext ctx
	= new GenericXmlApplicationContext("classpath:applicationContext.xml");//classpath: 실제 물리적인 경로와 상관없이 파일을 찾아줌 예:단축키
	
	//컨테이너 안의 TV라는 객체를 불러오라는 명령어(new 사용x)
	TV tv = (TV)ctx.getBean("tv"); //클래스의 이름만 넘김
	tv.turnOn();

	TV subTv = (TV)ctx.getBean(TV.class);//id가 업는 경우 클래스 정보 그 자체를 넘겨서 찾아오게 함.
	subTv.turnOn();
	
	//싱글톤(스프링은 오직 하나의 bean만 가지고 온다)
	if(tv == subTv) {
		System.out.println("같은 bean 입니다.");
	} else {
		System.out.println("다른 bean 입니다.");
	}
	

	
}
}
