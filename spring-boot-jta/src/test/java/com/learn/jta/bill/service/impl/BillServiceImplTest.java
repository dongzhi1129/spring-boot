package com.learn.jta.bill.service.impl;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learn.jta.bill.domain.Bill;
import com.learn.jta.bill.service.BillService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillServiceImplTest {
	
	@Autowired
	private BillService billService;

	@Test
	public void testInsertBill() {
		Bill bill = new Bill();
		bill.setNumber((int) System.currentTimeMillis());
		bill.setStatus((byte) 0);
		try {
			billService.insertBill(bill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
			e.printStackTrace();
		}
	}

}
