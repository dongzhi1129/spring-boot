package com.learn.jta.bill.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.jta.bill.domain.Bill;
import com.learn.jta.bill.mapper.BillMapper;
import com.learn.jta.bill.service.BillService;

@Service
public class BillServiceImpl implements BillService {

	@Resource
	private BillMapper billMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertBill(Bill bill) throws Exception {
		// TODO Auto-generated method stub
		int affectRows = billMapper.insertSelective(bill);
		if (affectRows <= 0) {
			throw new Exception("insert bill failed.");
		}

	}

}
