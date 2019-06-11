package com.learn.jta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.jta.bill.domain.Bill;
import com.learn.jta.bill.service.BillService;
import com.learn.jta.repository.service.RepositoryService;
import com.learn.jta.service.XaService;

@Service
public class XaServiceImpl implements XaService {
	
	@Autowired
	private BillService billService;
	@Autowired
	private RepositoryService repositoryService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void generateBill() throws Exception {
		// TODO Auto-generated method stub
		Bill bill = new Bill();
		bill.setNumber((int) System.currentTimeMillis());
		bill.setStatus((byte) 0);
		try {
			billService.insertBill(bill);
			repositoryService.updateRepository(1, 3, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		

	}

}
