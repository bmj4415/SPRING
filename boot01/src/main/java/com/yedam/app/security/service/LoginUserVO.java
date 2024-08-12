package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter //인스턴스를 생성하면서 단 한번 값을 가지는걸로 충분함.(변동가능성 줄이기)
public class LoginUserVO implements UserDetails {
	
	private UserVO userVO;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //Collection : Collection을 상속받은 모든 클래스들이 return값을 사용할 수 있고, GrantedAuthority를 상속??  
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		return auths;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() { //계정 만료여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //계정 잠금여부(ex) 사이트의 규칙을 지키지 않은 회원의 계정 잠금))
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //계정 패스워드 만료여부(주기적인 비밀번호 변경안내)
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 사용여부
		return true;
	}

}
