package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 비밀번호를 기반으로 로그인할 때 암호화된 비밀번호로 로그인시킴
	}

	// 인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http //Security가 적용될 URI
		.authorizeHttpRequests((authrize) -> authrize.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()//permitAll : 인증받지 않은 모든 사람을 포함. 즉, 인증/인가와 상관없이 접근하는 모든 사람
													 .requestMatchers("/", "/all").permitAll() //requestMatchers : 특정 경로에 대한 권한을 설정. 경로-권한이 한 쌍으로 움직임. 어떤 권한을 가진 사람이 어떤 경로를 이용가능한지.
													 .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") //hasAuthority로 사용한다면 (ROLE_USER)여야함. 둘 다 같은 메소드임. ** => 하위경로까지
													 .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
													 .anyRequest().authenticated() // anyRequest : 위 2가지 경우가 아니라면 사용. authenticated : 사용자가 인증되었음을 의미하며, 인증된 사용자만 접근할 수 있는 경로를 지정할 때 사용
																	

		)
		.formLogin(formlogin -> formlogin.defaultSuccessUrl("/all")) //default값이 formLogin 페이지를 활성화해줘야함.
		.logout(logout -> logout.logoutSuccessUrl("/all").invalidateHttpSession(true));
		
		//http.csrf(csrf -> csrf.disable());
		return http.build();
	}
	
	//프로젝트를 시작하는 시점에 회원정보를 등록하는 방법(메모리상 인증정보 등록 방법, 간단한 test를 진행할 때 사용)
	
	/*
	@Bean
	//인스턴스가 가져야할 초기값을 setting
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
							   .username("user1")
							   .password(passwordEncoder().encode("1234"))
							   .roles("USER") //ROLE_USER
							   //.authorities("ROLE_USER")
							   .build();
		UserDetails admin = User.builder()
								.username("admin1")
								.password(passwordEncoder().encode("1234"))
								//.roles("ADMIN") // ROLE_ADMIN
								.authorities("ROLE_ADMIN", "ROLE_USER")
								.build();
	return new InMemoryUserDetailsManager(user, admin);
	}
	*/
}
