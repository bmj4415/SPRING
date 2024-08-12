package com.yedam.app.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	private UserMapper userMapper;
	
	@Autowired
	CustomerUserDetailsService(UserMapper userMapper){
		this.userMapper = userMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //userdetails는 db에 해당 id의 여부만 체크(비교는 provider)
		// Mapper를 활용해서 DB에 접근
		UserVO userVO = userMapper.getUserInfo(username);
		
		if(userVO == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new LoginUserVO(userVO);
	}

}
