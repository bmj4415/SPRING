package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yedam.app.emp.service.EmpVO;

@Aspect //aop의 설정 담당(configuration이 아니라 component, 일반 bean과 기능이 동일)
@Component
public class CommonAspect {
	//포인트 컷 : 조인포인트 중에서 Advice(횡단관심)이 적용될 메소드 필터
	@Pointcut("within(com.yedam.app.emp.service.impl.*)") // 검색조건(within) : 지정한 경로 아래에 있는 모든 비즈니스 메소드를 총칭함.
	public void empPointCut() {} //포인트 컷 메소드가 적용되는 곳은 빈값

	//Weaving : 포인트 컷 + 타이밍 + Advice(횡단관심)
	@Before("empPointCut()") //동작시점이 각 어노테이션으로 존재함. //before : 서비스를 실행하기 전 사전작업이 필요할 때 사용
	public void beforeAdvice(JoinPoint joinPoint) { //joinPoint : 서비스 메소드
		String sinagerStr = joinPoint.getSignature().toString();
		Object[] args = joinPoint.getArgs(); //매개변수를 가져오는 부분
		System.err.println("####### 실행 : " + sinagerStr);
		for (Object arg : args) {
			System.err.println("매개변수");
			if (arg instanceof Integer) {
				System.out.println((Integer) arg);
			} else if (arg instanceof EmpVO) {
				System.err.println((EmpVO) arg);
			}
		}
	}
	//
	@Around("empPointCut()") //주로 실행 시간을 확인하고자 할 때 사용 多
	public Object executeTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String signaterStr = joinPoint.getSignature().toString();
		System.out.println("=== 시작 : " + signaterStr);
		
		//공통기능
		System.out.println("=== 핵심 기능 전 실행 : " + System.currentTimeMillis());
		try {
			//비즈니스 메소드를 실행
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			//공통기능
			System.out.println("=== 핵심 기능 후 실행 : " + System.currentTimeMillis());
			System.out.println("=== 끝 : " + signaterStr);
		}
	}
	
}
