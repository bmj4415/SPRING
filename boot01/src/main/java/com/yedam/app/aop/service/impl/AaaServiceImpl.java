package com.yedam.app.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AaaMapper;
import com.yedam.app.aop.service.AaaService;

@Service
public class AaaServiceImpl implements AaaService {
	private AaaMapper aaaMapper;

	@Autowired
	AaaServiceImpl(AaaMapper aaaMapper) {
		this.aaaMapper = aaaMapper;
	}

	@Transactional //@Service 아래에서 사용 ==> 트랜잭션으로 묶어주기 때문에 메소드 아래의 쿼리문이 함께 움직여야 할 때 유용함
	@Override
	public void insert() {
		aaaMapper.aaaInsert("101");
		aaaMapper.aaaInsert("a101");
	}

}
