package com.learn.jta.bill.service;

import com.learn.jta.bill.domain.Bill;

public interface BillService {
	
	void insertBill(Bill bill) throws Exception;

}
